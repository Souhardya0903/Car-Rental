package com.example.carrental.dto;

import lombok.Data;

@Data
public class ReviewResponse {
    private Long id;
    private int rating;
    private String comment;
    private String userName;
}
