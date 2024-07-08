package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Course;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Instructor;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.InstructorDetail;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.exceptions.EntityNotFoundException;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.repository.CourseRepository;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.repository.InstructorDetailRepository;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.repository.InstructorRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Data
@Accessors(chain = true)
@Service
public class InstructorService {

    private InstructorRepository instructorRepository;
    private InstructorDetailRepository instructorDetailRepository;
    private CourseRepository courseRepository;

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    private Instructor checkIfInstructorExistsAndReturn(Integer id) {
        Optional<Instructor> maybeInstructor = instructorRepository.findById(id);
        if (maybeInstructor.isEmpty()) {
            throw new EntityNotFoundException("Instructor does not exist with id " + id);
        }
        return maybeInstructor.get();
    }

    public Instructor getInstructorById(Integer id) {
        return checkIfInstructorExistsAndReturn(id);
    }

    public Instructor getInstructorById(Integer id, Boolean fetchCourses) {
        Instructor instructor = checkIfInstructorExistsAndReturn(id);
        if (fetchCourses) {
            instructor.setCourses(findCoursesByInstructorId(id));
        }
        return instructor;
    }

    public Instructor addInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructorById(Integer id, Instructor instructor) {
        checkIfInstructorExistsAndReturn(id);
        return instructorRepository.save(instructor.setId(id));
    }

    public Instructor attachDetailToInstructor(Integer instructorId, Integer detailsId) {
        Instructor instructor = checkIfInstructorExistsAndReturn(instructorId);
        Optional<InstructorDetail> maybeInstructorDetail = instructorDetailRepository.findById(detailsId);
        if (maybeInstructorDetail.isEmpty()) {
            throw new EntityNotFoundException("Instructor detail does not exist with id " + detailsId);
        }
        instructor.setInstructorDetail(maybeInstructorDetail.get());
        return updateInstructorById(instructorId, instructor);
    }

    private Instructor attachCoursesToInstructor(Integer instructorId, List<Integer> courseIds) {
        Instructor instructor = getInstructorById(instructorId);
        instructor.addCourses(courseRepository.findAllById(courseIds));
        return updateInstructorById(instructorId, instructor);
    }

    private Instructor detachCoursesFromInstructor(Integer instructorId, List<Integer> courseIds) {
        Instructor instructor = getInstructorById(instructorId);
        instructor.removeCourses(courseRepository.findAllById(courseIds));
        return updateInstructorById(instructorId, instructor);
    }

    public Instructor deleteInstructorById(Integer id) {
        Instructor deletedInstructor = getInstructorById(id);
        deletedInstructor.getCourses().stream().forEach(course -> course.setInstructor(null));
        instructorRepository.deleteById(id);
        return deletedInstructor;
    }

    public Instructor updateInstructorById(Integer id, Optional<Instructor> maybeInstructor,
            Optional<Integer> maybeDetailId, Optional<List<Integer>> maybeCourseId,
            Optional<List<Integer>> maybeRemoveCourseId) {
        if (maybeInstructor.isPresent()) {
            updateInstructorById(id, maybeInstructor.get());
        }
        if (maybeCourseId.isPresent()) {
            attachCoursesToInstructor(id, maybeCourseId.get());
        }
        if (maybeDetailId.isPresent()) {
            attachDetailToInstructor(id, maybeDetailId.get());
        }
        if (maybeRemoveCourseId.isPresent()) {
            detachCoursesFromInstructor(id, maybeRemoveCourseId.get());
        }
        return getInstructorById(id);
    }

    public List<Course> findCoursesByInstructorId(Integer id) {
        return courseRepository.findCoursesByInstructor(getInstructorById(id));
    }

}
