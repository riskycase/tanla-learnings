package com.tanlaplatforms.hrishikeshpatil.employeeapp.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tanlaplatforms.hrishikeshpatil.employeeapp.exceptions.EmployeeException;
import com.tanlaplatforms.hrishikeshpatil.employeeapp.exceptions.EmployeeNotFoundException;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeException> handleEmployeeNotFoundException(
            EmployeeNotFoundException employeeNotFoundException) {
        return new ResponseEntity<EmployeeException>(new EmployeeException(HttpStatus.NOT_FOUND.value(),
                employeeNotFoundException.getMessage(), System.currentTimeMillis()), HttpStatus.NOT_FOUND);
    }
}
