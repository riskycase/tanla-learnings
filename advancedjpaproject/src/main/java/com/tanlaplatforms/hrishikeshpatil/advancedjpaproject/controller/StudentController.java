package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Course;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Student;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.service.StudentService;

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
@RequestMapping("/api/students")
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class StudentController {

    private StudentService studentService;

    @GetMapping("")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id,
            @RequestParam(name = "courses", defaultValue = "false") Boolean courses) {
        return studentService.getStudentById(id, courses);
    }

    @GetMapping("/{id}/courses")
    public List<Course> getCoursesByStudentId(@PathVariable Integer id) {
        return studentService.findCoursesByStudentId(id);
    }

    @PostMapping("")
    public Student addNewStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/{id}")
    public Student editStudent(@PathVariable Integer id, @RequestBody Optional<Student> maybeStudent,
            @RequestParam(name = "courseId") Optional<List<Integer>> maybeCourseId,
            @RequestParam(name = "removeCourseId") Optional<List<Integer>> maybeRemoveCourseId) {
        return studentService.updateStudentById(id, maybeStudent, maybeCourseId, maybeRemoveCourseId);
    }

    @DeleteMapping("/{id}")
    public Student deleteStudentById(@PathVariable Integer id) {
        return studentService.deleteStudentById(id);
    }

}
