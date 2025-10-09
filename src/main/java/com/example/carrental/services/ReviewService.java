package com.example.carrental.services;

import com.example.carrental.dto.ReviewRequest;
import com.example.carrental.dto.ReviewResponse;
import com.example.carrental.entities.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewRequest request);
    List<ReviewResponse> getReviewsByCarId(Long carId);
}
