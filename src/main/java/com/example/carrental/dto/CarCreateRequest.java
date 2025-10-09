package com.example.carrental.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CarCreateRequest {
    @NotBlank(message = "Required")
    private String make;

    @NotBlank(message = "Required")
    private String model;

    @Min(value = 1900, message = "Invalid year")
    private int year;

    @Min(value = 1, message = "Invalid price")
    private double price;

    private String specifications;
    private String imageUrl;
}
