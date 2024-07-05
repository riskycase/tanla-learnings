package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Instructor;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.service.InstructorService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/instructors")
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class InstructorController {

    private InstructorService instructorService;

    @GetMapping("")
    public List<Instructor> getAllInstructors() {
        return instructorService.getAllInstructors();
    }

    @GetMapping("/{id}")
    public Instructor getInstructorById(@PathVariable Integer id) {
        return instructorService.getInstructorById(id);
    }

    @PostMapping("")
    public Instructor addNewInstructor(@RequestBody Instructor instructor) {
        return instructorService.addInstructor(instructor);
    }

    @PutMapping("/{id}")
    public Instructor editInstructor(@RequestBody Instructor instructor, @PathVariable Integer id) {
        return instructorService.updateInstructorById(id, instructor);
    }

    @DeleteMapping("/{id}")
    public Instructor deleteInstructorById(@PathVariable Integer id) {
        return instructorService.deleteInstructorById(id);
    }

    @PostMapping("/{id}")
    public Instructor addInstructorDetailsToInstructor(@PathVariable Integer id, @RequestParam Integer detailId) {
        return instructorService.attachDetailToInstructor(id, detailId);
    }

}
