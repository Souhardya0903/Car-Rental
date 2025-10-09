package com.example.carrental.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * This DTO represents the safe and curated data for a booking that is sent to the client.
 */
@Data
public class BookingResponse {

    private Long id;
    private LocalDate pickupDate;
    private LocalDate dropoffDate;
    private Double totalPrice;
    private String bookingStatus;

    // Convenient fields from the related Car entity
    private Long carId;
    private String carMake;
    private String carModel;

    // Convenient fields from the related Users entity
    private Long userId;
    private String userName;
}
