package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Course;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Review;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Student;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.service.CourseService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CourseController {

    private CourseService courseService;

    @GetMapping("")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/{id}/reviews")
    public List<Review> getCourseReviewsById(@PathVariable Integer id) {
        return courseService.findReviewsByCourseId(id);
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudentsByCourseId(@PathVariable Integer id) {
        return courseService.findStudentsByCourseId(id);
    }

    @PostMapping("")
    public Course addNewCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @PutMapping("/{id}")
    public Course editCourse(@RequestBody Optional<Course> maybeCourse, @PathVariable Integer id,
            @RequestParam(name = "reviewId") Optional<List<Integer>> maybeReviewId,
            @RequestParam(name = "removeReviewId") Optional<List<Integer>> maybeRemoveReviewId,
            @RequestParam(name = "studentId") Optional<List<Integer>> maybeStudentId,
            @RequestParam(name = "removeStudentId") Optional<List<Integer>> maybeRemoveStudentId) {
        return courseService.updateCourseById(id, maybeCourse, maybeReviewId, maybeRemoveReviewId, maybeStudentId,
                maybeRemoveStudentId);
    }

    @DeleteMapping("/{id}")
    public Course deleteCourseById(@PathVariable Integer id) {
        return courseService.deleteCourseById(id);
    }

}
