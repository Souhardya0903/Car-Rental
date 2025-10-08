package com.example.carrental.services;

import com.example.carrental.dtos.BookingRequest;
import com.example.carrental.entities.Booking;
import com.example.carrental.entities.Car;
import com.example.carrental.entities.User;
import com.example.carrental.exceptions.BookingNotFoundException;
import com.example.carrental.exceptions.CarNotFoundException;
import com.example.carrental.exceptions.UserNotFoundException;
import com.example.carrental.repositories.BookingRepository;
import com.example.carrental.repositories.CarRepository;
import com.example.carrental.repositories.UserRepository;
import com.example.carrental.util.BookingsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Booking createBooking(BookingRequest bookingRequest) {
        User user=userRepository.findById(bookingRequest.getCarId()).orElseThrow(()-> new UserNotFoundException("Wrong user id entered"));
        Car car = carRepository.findById(bookingRequest.getCarId()).orElseThrow(()-> new CarNotFoundException("Could not find requested car"));
        Booking booking=BookingsConverter.ConvertBookingRequestToBooking(bookingRequest,car,user);
        car.setAvailable(false);
        carRepository.save(car);
        booking.setBookingStatus("Processing");
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(()->new BookingNotFoundException("Could not find the booking with id:"+bookingId));
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()->new BookingNotFoundException("Could not find the booking with id:"+bookingId));
        if (booking != null) {
            Car car = booking.getCar();
            car.setAvailable(true);
            carRepository.save(car);
            booking.setBookingStatus("Cancelled");
            bookingRepository.save(booking);
        }
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}
