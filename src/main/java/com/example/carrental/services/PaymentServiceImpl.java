package com.example.carrental.services;

import com.example.carrental.dto.PaymentResponse;
import com.example.carrental.entities.Booking;
import com.example.carrental.entities.Payment;
import com.example.carrental.exceptions.BookingNotFoundException;
import com.example.carrental.exceptions.PaymentNotFoundException;
import com.example.carrental.repositories.BookingRepository;
import com.example.carrental.repositories.PaymentRepository;
import com.example.carrental.util.PaymentConverter;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public PaymentResponse calculateBill(Long bookingId) {
        // Step 1: Find the booking that needs a bill.
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking could not be found"));

        // Step 2: Create a new payment record.
        Payment payment = new Payment();

        // Step 3: Set the amount directly from the booking's pre-calculated total price.
        payment.setAmount(booking.getTotalPrice());

        // Step 4: Set the initial status and link it to the booking.
        payment.setPaymentStatus("Pending");
        payment.setBooking(booking);

        // Step 5: Save the new payment record.
        paymentRepository.save(payment);

        // Step 6: Convert to a response DTO and return.
        return PaymentConverter.paymentToPaymentResponse(payment);
    }

    @Override
    public PaymentResponse makePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment could not be found"));

        // When payment is made, update the status of both the payment and the booking.
        payment.setPaymentStatus("Paid");
        payment.getBooking().setBookingStatus("Booked");
        bookingRepository.save(payment.getBooking());
        paymentRepository.save(payment);

        // Convert the final Payment entity to a PaymentResponse DTO before returning.
        return PaymentConverter.paymentToPaymentResponse(payment);
    }
}
