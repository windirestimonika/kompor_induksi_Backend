package com.iconpln.kompor_induksi_Backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = 8663979294048343844L;
    public UserAlreadyExistsException(String message){super(message);}
    public UserAlreadyExistsException(String message, Throwable throwable){super(message, throwable);}
}
