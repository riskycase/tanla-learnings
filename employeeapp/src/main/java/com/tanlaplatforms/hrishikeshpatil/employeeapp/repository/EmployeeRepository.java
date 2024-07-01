package com.tanlaplatforms.hrishikeshpatil.employeeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanlaplatforms.hrishikeshpatil.employeeapp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
