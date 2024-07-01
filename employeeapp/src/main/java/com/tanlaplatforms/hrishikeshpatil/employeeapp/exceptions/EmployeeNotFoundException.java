package com.tanlaplatforms.hrishikeshpatil.employeeapp.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public EmployeeNotFoundException(Throwable throwable) {
        super(throwable);
    }

}
