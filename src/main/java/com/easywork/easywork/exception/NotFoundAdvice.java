package com.easywork.easywork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PayrollNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse payrollNotFoundHandler(PayrollNotFoundException ex){
        return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString());
    }
}
