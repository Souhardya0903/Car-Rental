package com.example.carrental.services;

import com.example.carrental.entities.Booking;
import com.example.carrental.entities.Car;
import com.example.carrental.repositories.BookingRepository;
import com.example.carrental.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public Booking createBooking(Booking booking) {
        Car car = booking.getCar();
        car.setAvailable(false);
        carRepository.save(car);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            Car car = booking.getCar();
            car.setAvailable(true);
            carRepository.save(car);
            bookingRepository.deleteById(bookingId);
        }
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}
