package com.example.carrental.services;

import com.example.carrental.dto.PaymentResponse;
import com.example.carrental.entities.Booking;
import com.example.carrental.entities.Payment;
import com.example.carrental.exceptions.BookingNotFoundException;
import com.example.carrental.exceptions.PaymentNotFoundException;
import com.example.carrental.repositories.BookingRepository;
import com.example.carrental.repositories.PaymentRepository;
import com.example.carrental.util.PaymentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepo;

    @Override
    public PaymentResponse calculateBill(Long bookingId) {
        Payment payment = new Payment();
        Booking booking = bookingRepo.findById(bookingId).orElseThrow(()->new BookingNotFoundException("Booking could not be found"));
        long tripDuration= ChronoUnit.DAYS.between(booking.getPickupDate(), booking.getDropoffDate());
        payment.setAmount(tripDuration*998.9);
        payment.setPaymentStatus("Pending");
        payment.setBooking(booking);
        paymentRepository.save(payment);
        return PaymentConverter.paymentToPaymentResponse(payment);
    }

    @Override
    public PaymentResponse makePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()->new PaymentNotFoundException("Payment could not be found"));
        payment.setPaymentStatus("Paid");
        payment.getBooking().setBookingStatus("Booked");
        bookingRepo.save(payment.getBooking());
        paymentRepository.save(payment);
        return PaymentConverter.paymentToPaymentResponse(payment);
    }
}
