package com.example.carrental.util;

import com.example.carrental.dto.BookingRequest;
import com.example.carrental.entities.Booking;
import com.example.carrental.entities.Car;
import com.example.carrental.entities.Users;

public class BookingsConverter {
    public static Booking ConvertBookingRequestToBooking(BookingRequest req, Car car, Users user){
        Booking booking = new Booking();
        booking.setPickupDate(req.getPickupDate());
        booking.setDropoffDate(req.getDropoffDate());
        booking.setCar(car);
        booking.setUsers(user);
        return booking;

    }
}
