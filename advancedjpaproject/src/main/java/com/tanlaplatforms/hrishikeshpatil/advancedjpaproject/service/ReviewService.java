package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Review;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.exceptions.EntityNotFoundException;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.repository.ReviewRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Data
@Accessors(chain = true)
@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    private Review checkIfReviewsExistsAndReturn(Integer id) {
        Optional<Review> maybeReview = reviewRepository.findById(id);
        if (maybeReview.isEmpty()) {
            throw new EntityNotFoundException("Review does not exist with id " + id);
        }
        return maybeReview.get();
    }

    public Review getReviewById(Integer id) {
        return checkIfReviewsExistsAndReturn(id);
    }

    public Review addReview(Review student) {
        return reviewRepository.save(student);
    }

    private Review updateReviewById(Integer id, Review student) {
        checkIfReviewsExistsAndReturn(id);
        return reviewRepository.save(student.setId(id));
    }

    public Review deleteReviewById(Integer id) {
        Review deletedReview = checkIfReviewsExistsAndReturn(id);
        reviewRepository.deleteById(id);
        return deletedReview;
    }

    public Review updateReviewById(Integer id, Optional<Review> maybeReview) {
        if (maybeReview.isPresent()) {
            updateReviewById(id, maybeReview.get());
        }
        return getReviewById(id);
    }

}
