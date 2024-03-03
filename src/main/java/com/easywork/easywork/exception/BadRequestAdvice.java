package com.easywork.easywork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BadRequestAdvice {
    @ResponseBody
    @ExceptionHandler(PayrollBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse PayrollBadRequestException(PayrollBadRequestException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.toString());
    }
}
