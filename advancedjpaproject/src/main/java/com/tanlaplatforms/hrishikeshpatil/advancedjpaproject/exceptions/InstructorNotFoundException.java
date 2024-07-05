package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.exceptions;

public class InstructorNotFoundException extends RuntimeException {

    public InstructorNotFoundException(String message) {
        super(message);
    }

    public InstructorNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InstructorNotFoundException(Throwable throwable) {
        super(throwable);
    }

}
