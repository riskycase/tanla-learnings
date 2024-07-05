package com.tanlaplatforms.hrishikeshpatil.advancedjpaproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class GenericExceptionResponse {
    private int code;
    private String message;
    private long timeStamp;
}
