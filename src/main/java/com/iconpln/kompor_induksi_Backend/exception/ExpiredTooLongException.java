package com.iconpln.kompor_induksi_Backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExpiredTooLongException extends RuntimeException {
    private static final long serialVersionUID = -4304978724482906578L;
    public ExpiredTooLongException(String message){
        super(message);
    }
    public ExpiredTooLongException(String message, Throwable throwable){
        super(message, throwable);
    }
}
