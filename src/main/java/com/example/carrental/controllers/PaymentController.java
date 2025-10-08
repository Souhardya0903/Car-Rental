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
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/calculate/{bookingId}")
    public ResponseEntity<PaymentResponse> calculateBill(@PathVariable Long bookingId){
        PaymentResponse paymentResponse = paymentService.calculateBill(bookingId);
        return ResponseEntity.ok(paymentResponse);
    }

    @PostMapping("/make-payment/{paymentId}")
    public ResponseEntity<Payment> makePayment(@PathVariable Long paymentId){
        Payment payment = paymentService.makePayment(paymentId);
        return ResponseEntity.ok(payment);
    }
}
