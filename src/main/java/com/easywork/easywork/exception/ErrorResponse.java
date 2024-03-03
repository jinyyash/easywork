package com.easywork.easywork.exception;

import java.sql.Timestamp;
import java.time.Instant;

public record ErrorResponse(String message, String response, Timestamp timestamp ) {
    public ErrorResponse(String message, String response){
        this(message,response,Timestamp.from(Instant.now()));
    }
}
