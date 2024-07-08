package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Course;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findReviewsByCourse(Course course);

}
