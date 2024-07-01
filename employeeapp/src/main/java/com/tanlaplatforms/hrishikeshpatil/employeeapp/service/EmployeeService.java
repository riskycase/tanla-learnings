package com.tanlaplatforms.hrishikeshpatil.employeeapp.service;

import java.util.List;
import java.util.Optional;

import com.tanlaplatforms.hrishikeshpatil.employeeapp.entity.Employee;
import com.tanlaplatforms.hrishikeshpatil.employeeapp.exceptions.EmployeeNotFoundException;
import com.tanlaplatforms.hrishikeshpatil.employeeapp.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Integer id) {
        Optional<Employee> maybeEmployee = employeeRepository.findById(id);
        if (maybeEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("No employee found with id: " + id);
        }
        return maybeEmployee.get();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee editEmployee(Integer id, Employee employee) {
        Optional<Employee> maybeEmployee = employeeRepository.findById(id);
        if (maybeEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("No employee found with id: " + id);
        }
        return employeeRepository.save(employee.setId(id));
    }

    public Employee deleteEmployeeById(Integer id) {
        Optional<Employee> maybeEmployee = employeeRepository.findById(id);
        if (maybeEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("No employee found with id: " + id);
        }
        employeeRepository.deleteById(id);
        return maybeEmployee.get();
    }

}
