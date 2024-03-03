package com.easywork.easywork.exception;

public class PayrollBadRequestException extends RuntimeException {
    public PayrollBadRequestException(String message) {
        super("Bad Request : " + message);
    }

}
