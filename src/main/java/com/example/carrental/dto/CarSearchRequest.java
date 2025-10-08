package com.example.carrental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CarSearchRequest {
    private String make;
    private String model;

    @NotBlank(message = "Mandatory")
    private String Location;

    @NotNull
    private LocalDateTime pickupTime;

    @NotNull
    private LocalDateTime dropofftime;
    private Boolean isAvailable=true;
}
