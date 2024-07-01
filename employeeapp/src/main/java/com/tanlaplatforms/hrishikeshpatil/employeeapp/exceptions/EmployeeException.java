package com.tanlaplatforms.hrishikeshpatil.employeeapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class EmployeeException {
    private int code;
    private String message;
    private long timeStamp;
}
