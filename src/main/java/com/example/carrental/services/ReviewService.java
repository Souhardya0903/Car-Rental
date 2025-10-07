package com.example.carrental.services;

import com.example.carrental.entities.Review;

import java.util.List;

public interface ReviewService {

    Review addReview(Review review);

    List<Review> getReviewsByCarId(Long carId);
}
