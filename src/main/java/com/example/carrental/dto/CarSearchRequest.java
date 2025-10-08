package com.example.carrental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CarSearchRequest {
    private String make;
    private String model;

    @NotBlank(message = "Mandatory")
    private String Location;

    @NotNull
    private LocalDate pickupTime;

    @NotNull
    private LocalDate dropofftime;
    private Boolean isAvailable;
}
