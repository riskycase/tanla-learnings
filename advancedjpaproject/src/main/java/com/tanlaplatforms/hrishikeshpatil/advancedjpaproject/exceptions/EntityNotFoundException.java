package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public EntityNotFoundException(Throwable throwable) {
        super(throwable);
    }

}
