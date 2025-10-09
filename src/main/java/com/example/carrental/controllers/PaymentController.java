package com.example.carrental.controllers;

import com.example.carrental.dto.PaymentResponse;
import com.example.carrental.entities.Payment;
import com.example.carrental.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/calculate/{bookingId}")
    public ResponseEntity<PaymentResponse> calculateBill(@PathVariable Long bookingId){
        PaymentResponse paymentResponse = paymentService.calculateBill(bookingId);
        return ResponseEntity.ok(paymentResponse);
    }

    @GetMapping("/make-payment/{paymentId}")
    public ResponseEntity<PaymentResponse> makePayment(@PathVariable Long paymentId){
        PaymentResponse payment = paymentService.makePayment(paymentId);
        return ResponseEntity.ok(payment);
    }
}
