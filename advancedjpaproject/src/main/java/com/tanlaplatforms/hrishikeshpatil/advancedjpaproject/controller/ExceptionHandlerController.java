package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.exceptions.GenericExceptionResponse;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.exceptions.InstructorDetailNotFoundException;
import com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.exceptions.InstructorNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    public ResponseEntity<GenericExceptionResponse> handleInstructorNotFoundException(
            InstructorNotFoundException instructorNotFoundException) {
        return new ResponseEntity<GenericExceptionResponse>(new GenericExceptionResponse(HttpStatus.NOT_FOUND.value(),
                instructorNotFoundException.getMessage(), System.currentTimeMillis()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<GenericExceptionResponse> handleInstructorDetailNotFoundException(
            InstructorDetailNotFoundException instructorDetailNotFoundException) {
        return new ResponseEntity<GenericExceptionResponse>(new GenericExceptionResponse(HttpStatus.NOT_FOUND.value(),
                instructorDetailNotFoundException.getMessage(), System.currentTimeMillis()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<GenericExceptionResponse> handleException(Exception exception) {
        return new ResponseEntity<GenericExceptionResponse>(new GenericExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }

}
