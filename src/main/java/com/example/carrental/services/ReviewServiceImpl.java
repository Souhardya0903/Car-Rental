package com.example.carrental.services;

import com.example.carrental.dto.ReviewRequest;
import com.example.carrental.dto.ReviewResponse;
import com.example.carrental.entities.Car;
import com.example.carrental.entities.Review;
import com.example.carrental.entities.Users;
import com.example.carrental.exceptions.CarNotFoundException;
import com.example.carrental.exceptions.UsersNotFoundException;
import com.example.carrental.repositories.CarRepository;
import com.example.carrental.repositories.ReviewRepository;
import com.example.carrental.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;


    @Override
    public Review createReview(ReviewRequest request) {
        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UsersNotFoundException("User not found with id: " + request.getUserId()));

        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new CarNotFoundException("Car not found with id: " + request.getCarId()));

        Review review = new Review();
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setUsers(user);
        review.setCar(car);

        return reviewRepository.save(review);
    }

    @Override
    public List<ReviewResponse> getReviewsByCarId(Long carId) {
        List<Review> reviews = reviewRepository.findByCarId(carId);

        return reviews.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private ReviewResponse convertToResponse(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.setId(review.getId());
        response.setRating(review.getRating());
        response.setComment(review.getComment());
        response.setUserName(review.getUsers().getFirstName());
        return response;
    }
}
