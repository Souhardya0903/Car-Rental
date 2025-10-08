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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(CarNotFoundException ex, WebRequest request){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
