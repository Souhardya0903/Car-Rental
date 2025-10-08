package com.example.carrental.util;

import com.example.carrental.dto.PaymentResponse;
import com.example.carrental.entities.Payment;

public class PaymentConverter {
    public static PaymentResponse paymentToPaymentResponse(Payment payment){
        PaymentResponse paymentResponse=new PaymentResponse();
        paymentResponse.setPaymentStatus(payment.getPaymentStatus());
        paymentResponse.setTransactionId(paymentResponse.getTransactionId());
        paymentResponse.setAmount(paymentResponse.getAmount());
        return paymentResponse;
    }
}
