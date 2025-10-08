package com.example.carrental.services;

import com.example.carrental.dtos.BookingRequest;
import com.example.carrental.entities.Booking;

import java.util.List;

public interface BookingService {

    Booking createBooking(BookingRequest booking);

    Booking getBookingById(Long bookingId);

    void cancelBooking(Long bookingId);

    List<Booking> getBookingsByUserId(Long userId);
}
