package com.example.carrental.controllers;

import com.example.carrental.dto.ReviewRequest;
import com.example.carrental.dto.ReviewResponse;
import com.example.carrental.entities.Review;
import com.example.carrental.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest request) {
        Review createdReview = reviewService.createReview(request);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByCarId(@PathVariable Long carId) {
        List<ReviewResponse> reviews = reviewService.getReviewsByCarId(carId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
