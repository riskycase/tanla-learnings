package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Instructor;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.InstructorDetail;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.exceptions.InstructorDetailNotFoundException;
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

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    private Instructor checkIfInstructorExistsAndReturn(Integer id) {
        Optional<Instructor> maybeInstructor = instructorRepository.findById(id);
        if (maybeInstructor.isEmpty()) {
            throw new InstructorDetailNotFoundException("Instructor does not exist with id " + id);
        }
        return maybeInstructor.get();
    }

    private InstructorDetail checkIfInstructorDetailsExistsAndReturn(Integer id) {
        Optional<InstructorDetail> maybeInstructorDetail = instructorDetailRepository.findById(id);
        if (maybeInstructorDetail.isEmpty()) {
            throw new InstructorDetailNotFoundException("Instructor detail does not exist with id " + id);
        }
        return maybeInstructorDetail.get();
    }

    public Instructor getInstructorById(Integer id) {
        return checkIfInstructorExistsAndReturn(id);
    }

    public Instructor addInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructorById(Integer id, Instructor instructor) {
        checkIfInstructorExistsAndReturn(id);
        return instructorRepository.save(instructor.setId(id));
    }

    public Instructor attachDetailToInstructor(Integer instructorId, Integer detailsId) {
        InstructorDetail instructorDetail = checkIfInstructorDetailsExistsAndReturn(detailsId);
        Instructor instructor = checkIfInstructorExistsAndReturn(instructorId);
        instructor.setInstructorDetail(instructorDetail);
        return instructorRepository.save(instructor);
    }

    public Instructor deleteInstructorById(Integer id) {
        Instructor deletedInstructor = checkIfInstructorExistsAndReturn(id);
        instructorRepository.deleteById(id);
        return deletedInstructor;
    }

}
