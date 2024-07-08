package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Course;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Instructor;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findCoursesByInstructor(Instructor instructor);

    @Query("SELECT c FROM Course c JOIN FETCH Student s where s.id = :id")
    public List<Course> findCoursesByStudentId(Integer id);

}
