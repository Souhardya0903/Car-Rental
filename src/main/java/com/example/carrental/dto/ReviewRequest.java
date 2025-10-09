package com.example.carrental.dto;

import lombok.Data;

@Data
public class ReviewRequest {
    private Long userId;
    private Long carId;
    private int rating;
    private String comment;
}
