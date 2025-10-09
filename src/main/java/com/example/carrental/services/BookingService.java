package com.example.carrental.services;

import com.example.carrental.dto.BookingRequest;
import com.example.carrental.dto.BookingResponse;
import com.example.carrental.entities.Booking;

import java.util.List;

public interface BookingService {

    BookingResponse createBooking(BookingRequest booking);

    BookingResponse getBookingById(Long bookingId);

    void cancelBooking(Long bookingId);

    List<BookingResponse> getBookingsByUsersId(Long userId);
}
