package com.example.carrental.dto;

import com.example.carrental.entities.Booking;
import lombok.Data;


@Data
public class PaymentResponse {
    private Long transactionId;
    private double amount;
    private String paymentStatus;
}
