package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s JOIN FETCH Course c where c.id = :id")
    public List<Student> findStudentsByCourseId(Integer id);

}
