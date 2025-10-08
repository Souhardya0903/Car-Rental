package com.example.carrental.services;

import com.example.carrental.dto.PaymentResponse;
import com.example.carrental.entities.Payment;

public interface PaymentService {
    public PaymentResponse calculateBill(Long bookingId);
    public Payment makePayment(Long paymentId);
}
