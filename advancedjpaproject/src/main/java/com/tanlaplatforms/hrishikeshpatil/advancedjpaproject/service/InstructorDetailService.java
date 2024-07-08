package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.InstructorDetail;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.exceptions.EntityNotFoundException;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.repository.InstructorDetailRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Data
@Accessors(chain = true)
@Service
public class InstructorDetailService {

    private InstructorDetailRepository instructorDetailRepository;

    public List<InstructorDetail> getAllInstructorDetails() {
        return instructorDetailRepository.findAll();
    }

    private InstructorDetail checkIfInstructorDetailsExistsAndReturn(Integer id) {
        Optional<InstructorDetail> maybeInstructorDetail = instructorDetailRepository.findById(id);
        if (maybeInstructorDetail.isEmpty()) {
            throw new EntityNotFoundException("Instructor detail does not exist with id " + id);
        }
        return maybeInstructorDetail.get();
    }

    public InstructorDetail getInstructorDetailById(Integer id) {
        return checkIfInstructorDetailsExistsAndReturn(id);
    }

    public InstructorDetail addInstructorDetail(InstructorDetail instructorDetail) {
        return instructorDetailRepository.save(instructorDetail);
    }

    public InstructorDetail updateInstructorDetailById(Integer id, InstructorDetail instructorDetail) {
        checkIfInstructorDetailsExistsAndReturn(id);
        return instructorDetailRepository.save(instructorDetail.setId(id));
    }

    public InstructorDetail deleteInstructorDetailById(Integer id) {
        InstructorDetail deletedInstructorDetail = checkIfInstructorDetailsExistsAndReturn(id);
        instructorDetailRepository.deleteById(id);
        return deletedInstructorDetail;
    }

    public InstructorDetail updateInstructorDetailById(Integer id, Optional<InstructorDetail> maybeInstructorDetail) {
        if (maybeInstructorDetail.isPresent()) {
            updateInstructorDetailById(id, maybeInstructorDetail.get());
        }
        return getInstructorDetailById(id);
    }

}
