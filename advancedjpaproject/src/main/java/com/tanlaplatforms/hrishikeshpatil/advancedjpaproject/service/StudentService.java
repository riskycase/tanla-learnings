package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Course;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Student;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.exceptions.EntityNotFoundException;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.repository.CourseRepository;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.repository.StudentRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Data
@Accessors(chain = true)
@Service
public class StudentService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    private Student checkIfStudentsExistsAndReturn(Integer id) {
        Optional<Student> maybeStudent = studentRepository.findById(id);
        if (maybeStudent.isEmpty()) {
            throw new EntityNotFoundException("Student does not exist with id " + id);
        }
        return maybeStudent.get();
    }

    public Student getStudentById(Integer id) {
        return checkIfStudentsExistsAndReturn(id);
    }

    public Student getStudentById(Integer id, Boolean fetchCourses) {
        Student student = getStudentById(id);
        if (fetchCourses) {
            student.setCourses(courseRepository.findCoursesByStudentId(id));
        }
        return student;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudentById(Integer id, Student student) {
        checkIfStudentsExistsAndReturn(id);
        return studentRepository.save(student.setId(id));
    }

    private Student enrollStudentsInCourse(Integer studentId, List<Integer> courses) {
        Student student = getStudentById(studentId);
        student.addCourses(courseRepository.findAllById(courses));
        return updateStudentById(studentId, student);
    }

    private Student unenrollStudentsInCourse(Integer studentId, List<Integer> courses) {
        Student student = getStudentById(studentId);
        student.removeCourses(courseRepository.findAllById(courses));
        return updateStudentById(studentId, student);
    }

    public Student deleteStudentById(Integer id) {
        Student deletedStudent = checkIfStudentsExistsAndReturn(id);
        studentRepository.deleteById(id);
        return deletedStudent;
    }

    public Student updateStudentById(Integer studentId, Optional<Student> maybeStudent,
            Optional<List<Integer>> maybeCourseId, Optional<List<Integer>> maybeRemoveCourseId) {
        if (maybeStudent.isPresent()) {
            updateStudentById(studentId, maybeStudent.get());
        }
        if (maybeCourseId.isPresent()) {
            enrollStudentsInCourse(studentId, maybeCourseId.get());
        }
        if (maybeRemoveCourseId.isPresent()) {
            unenrollStudentsInCourse(studentId, maybeRemoveCourseId.get());
        }
        return getStudentById(studentId);
    }

    public List<Course> findCoursesByStudentId(Integer studentId) {
        return courseRepository.findCoursesByStudentId(studentId);
    }

}
