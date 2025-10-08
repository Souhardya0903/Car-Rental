package com.example.carrental.dto;

import lombok.Data;

@Data
public class CarResponse {
    private Long id;
    private String make;
    private String model;
    private int year;
    private double price;
    private boolean isAvailable;
    private String specifications;
    private String imageUrl;
}
