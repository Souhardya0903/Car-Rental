package com.example.carrental.util;

import com.example.carrental.dtos.BookingRequest;
import com.example.carrental.entities.Booking;
import com.example.carrental.entities.Car;
import com.example.carrental.entities.User;

public class BookingsConverter {
    public static Booking ConvertBookingRequestToBooking(BookingRequest req, Car car, User user){
        Booking booking = new Booking();
        booking.setPickupDate(req.getPickupDate());
        booking.setDropoffDate(req.getDropoffDate());
        booking.setCar(car);
        booking.setUser(user);
        return booking;

    }
}
