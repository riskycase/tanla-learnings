package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.InstructorDetail;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.service.InstructorDetailService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/instructorDetails")
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class InstructorDetailController {

    private InstructorDetailService instructorDetailService;

    @GetMapping("")
    public List<InstructorDetail> getAllInstructorDetails() {
        return instructorDetailService.getAllInstructorDetailss();
    }

    @GetMapping("/{id}")
    public InstructorDetail getInstructorDetailById(@PathVariable Integer id) {
        return instructorDetailService.getInstructorDetailById(id);
    }

    @PostMapping("")
    public InstructorDetail addNewInstructorDetail(@RequestBody InstructorDetail instructor) {
        return instructorDetailService.addInstructorDetail(instructor);
    }

    @PutMapping("/{id}")
    public InstructorDetail editInstructorDetail(@RequestBody InstructorDetail instructor, @PathVariable Integer id) {
        return instructorDetailService.updateInstructorDetailById(id, instructor);
    }

    @DeleteMapping("/{id}")
    public InstructorDetail deleteInstructorDetailById(@PathVariable Integer id) {
        return instructorDetailService.deleteInstructorDetailById(id);
    }

}
