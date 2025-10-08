package com.example.carrental.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BookingRequest {
    private Date pickupDate;
    private Date dropoffDate;
    private Long carId;
    private Long userId;
}
