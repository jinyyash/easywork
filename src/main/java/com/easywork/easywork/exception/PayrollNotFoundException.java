package com.easywork.easywork.exception;

public class PayrollNotFoundException extends RuntimeException{

    public PayrollNotFoundException(Long id, String object){
        super("Could not find "+object+" with id : "  + id);
    }
}
