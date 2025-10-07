package com.example.carrental.controllers;

import com.example.carrental.entities.Review;
import com.example.carrental.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review newReview = reviewService.addReview(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<Review>> getReviewsByCarId(@PathVariable Long carId) {
        List<Review> reviews = reviewService.getReviewsByCarId(carId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
