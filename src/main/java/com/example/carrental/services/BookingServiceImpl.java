package com.example.carrental.services;

import com.example.carrental.dto.BookingRequest;
import com.example.carrental.entities.Booking;
import com.example.carrental.entities.Car;
import com.example.carrental.entities.Users;
import com.example.carrental.exceptions.BookingNotFoundException;
import com.example.carrental.exceptions.CarNotFoundException;
import com.example.carrental.exceptions.UsersNotFoundException;
import com.example.carrental.repositories.BookingRepository;
import com.example.carrental.repositories.CarRepository;
import com.example.carrental.repositories.UserRepository;
import com.example.carrental.util.BookingsConverter;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, CarRepository carRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Booking createBooking(BookingRequest bookingRequest) {
        Users user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new UsersNotFoundException("Wrong user id entered"));
        Car car = carRepository.findById(bookingRequest.getCarId())
                .orElseThrow(() -> new CarNotFoundException("Could not find requested car"));

        // Calculate the number of days for the rental.
        long numberOfDays = ChronoUnit.DAYS.between(bookingRequest.getPickupDate(), bookingRequest.getDropoffDate());
        // Ensure a minimum of 1 day is charged.
        if (numberOfDays == 0) {
            numberOfDays = 1;
        }

        // Calculate the total price.
        double totalPrice = car.getPrice() * numberOfDays;

        // Use the converter to create the basic booking object.
        Booking booking = BookingsConverter.ConvertBookingRequestToBooking(bookingRequest, car, user);

        // Set the calculated total price and status.
        booking.setTotalPrice(totalPrice);
        booking.setBookingStatus("Processing");

        // Save the complete booking object to the database.
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException("Could not find the booking with id:" + bookingId));
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Could not find the booking with id:" + bookingId));

        // When a booking is cancelled, we just update its status.
        // We no longer need to manually manage the car's availability flag.
        booking.setBookingStatus("Cancelled");
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByUsersId(Long userId) {
        return bookingRepository.findByUsersId(userId);
    }
}
