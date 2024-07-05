package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.exceptions;

public class InstructorDetailNotFoundException extends RuntimeException {

    public InstructorDetailNotFoundException(String message) {
        super(message);
    }

    public InstructorDetailNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InstructorDetailNotFoundException(Throwable throwable) {
        super(throwable);
    }

}
