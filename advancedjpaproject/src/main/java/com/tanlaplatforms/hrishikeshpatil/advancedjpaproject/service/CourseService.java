package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Course;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Review;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Student;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.exceptions.EntityNotFoundException;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.repository.CourseRepository;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.repository.ReviewRepository;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.repository.StudentRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Data
@Accessors(chain = true)
@Service
public class CourseService {

    private CourseRepository courseRepository;
    private ReviewRepository reviewRepository;
    private StudentRepository studentRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    private Course checkIfCoursesExistsAndReturn(Integer id) {
        Optional<Course> maybeCourse = courseRepository.findById(id);
        if (maybeCourse.isEmpty()) {
            throw new EntityNotFoundException("Course does not exist with id " + id);
        }
        return maybeCourse.get();
    }

    public Course getCourseById(Integer id) {
        return checkIfCoursesExistsAndReturn(id);
    }

    public Course addCourse(Course student) {
        return courseRepository.save(student);
    }

    public Course updateCourseById(Integer id, Course student) {
        checkIfCoursesExistsAndReturn(id);
        return courseRepository.save(student.setId(id));
    }

    private Course attachReviewsToCourse(Integer courseId, List<Integer> reviews) {
        Course course = getCourseById(courseId);
        course.addReviews(reviewRepository.findAllById(reviews));
        return updateCourseById(courseId, course);
    }

    private Course detachReviewsFromCourse(Integer courseId, List<Integer> reviews) {
        Course course = getCourseById(courseId);
        course.removeReviews(reviewRepository.findAllById(reviews));
        return updateCourseById(courseId, course);
    }

    private Course enrollStudentsInCourse(Integer courseId, List<Integer> students) {
        Course course = getCourseById(courseId);
        course.addStudents(studentRepository.findAllById(students));
        return updateCourseById(courseId, course);
    }

    private Course unenrollStudentsInCourse(Integer courseId, List<Integer> students) {
        Course course = getCourseById(courseId);
        course.removeStudents(studentRepository.findAllById(students));
        return updateCourseById(courseId, course);
    }

    public Course updateCourseById(Integer id, Optional<Course> maybeCourse, Optional<List<Integer>> maybeReviewId,
            Optional<List<Integer>> maybeRemoveReviewId, Optional<List<Integer>> maybeStudentId,
            Optional<List<Integer>> maybeRemoveStudentId) {
        if (maybeCourse.isPresent()) {
            updateCourseById(id, maybeCourse.get());
        }
        if (maybeReviewId.isPresent()) {
            attachReviewsToCourse(id, maybeReviewId.get());
        }
        if (maybeRemoveReviewId.isPresent()) {
            detachReviewsFromCourse(id, maybeRemoveReviewId.get());
        }
        if (maybeStudentId.isPresent()) {
            enrollStudentsInCourse(id, maybeStudentId.get());
        }
        if (maybeRemoveStudentId.isPresent()) {
            unenrollStudentsInCourse(id, maybeRemoveStudentId.get());
        }
        return getCourseById(id);
    }

    public Course deleteCourseById(Integer id) {
        Course deletedCourse = checkIfCoursesExistsAndReturn(id);
        courseRepository.deleteById(id);
        return deletedCourse;
    }

    public List<Review> findReviewsByCourseId(Integer id) {
        return reviewRepository.findReviewsByCourse(getCourseById(id));
    }

    public List<Student> findStudentsByCourseId(Integer studentId) {
        return studentRepository.findStudentsByCourseId(studentId);
    }

}
