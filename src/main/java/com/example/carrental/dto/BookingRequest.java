package com.example.carrental.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BookingRequest {
    private LocalDate pickupDate;
    private LocalDate dropoffDate;
    private Long carId;
    private Long userId;
}
