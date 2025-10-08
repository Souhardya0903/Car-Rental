package com.example.carrental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<?> bookingNotFoundException(BookingNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<?> carNotFoundException(CarNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsersNotFoundException.class)
    public ResponseEntity<?> usersNotFoundException(UsersNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<?> paymentNotFoundException(PaymentNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
