package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Review;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/reviews")
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ReviewController {

    private ReviewService reviewService;

    @GetMapping("")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Integer id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping("")
    public Review addNewReview(@RequestBody Review course) {
        return reviewService.addReview(course);
    }

    @PutMapping("/{id}")
    public Review editReview(@RequestBody Optional<Review> maybeReview, @PathVariable Integer id) {
        return reviewService.updateReviewById(id, maybeReview);
    }

    @DeleteMapping("/{id}")
    public Review deleteReviewById(@PathVariable Integer id) {
        return reviewService.deleteReviewById(id);
    }

}
