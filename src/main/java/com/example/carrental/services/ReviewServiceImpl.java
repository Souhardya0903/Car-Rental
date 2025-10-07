package com.example.carrental.services;

import com.example.carrental.entities.Review;
import com.example.carrental.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsByCarId(Long carId) {
        return reviewRepository.findByCarId(carId);
    }
}
