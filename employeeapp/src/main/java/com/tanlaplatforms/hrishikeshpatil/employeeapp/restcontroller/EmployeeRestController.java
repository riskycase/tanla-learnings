package com.tanlaplatforms.hrishikeshpatil.employeeapp.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanlaplatforms.hrishikeshpatil.employeeapp.entity.Employee;
import com.tanlaplatforms.hrishikeshpatil.employeeapp.exceptions.EmployeeNotFoundException;
import com.tanlaplatforms.hrishikeshpatil.employeeapp.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeRestController {
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> maybeEmployee = employeeRepository.findById(id);
        if (maybeEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("No employee found with id: " + id);
        }
        return maybeEmployee.get();
    }

    @PostMapping("/")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee modifyEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        Optional<Employee> maybeEmployee = employeeRepository.findById(id);
        if (maybeEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("No employee found with id: " + id);
        }
        return employeeRepository.save(employee.setId(id));
    }

    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable Integer id) {
        Optional<Employee> maybeEmployee = employeeRepository.findById(id);
        if (maybeEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("No employee found with id: " + id);
        }
        employeeRepository.deleteById(id);
        return maybeEmployee.get();
    }

}
