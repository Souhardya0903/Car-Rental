package com.example.carrental.controllers;

import com.example.carrental.dto.BookingRequest;
import com.example.carrental.dto.BookingResponse;
import com.example.carrental.services.BookingService;
import com.example.carrental.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final PaymentService paymentService;

    public BookingController(BookingService bookingService, PaymentService paymentService) {
        this.bookingService = bookingService;
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest booking) {
        BookingResponse newBooking = bookingService.createBooking(booking);
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long bookingId) {
        BookingResponse booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByUserId(@PathVariable Long userId) {
        List<BookingResponse> bookings = bookingService.getBookingsByUsersId(userId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
}
