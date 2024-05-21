package com.immobylette.api.photo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthWrongException extends RuntimeException{
    public AuthWrongException() {
        super("Authorization header is wrong");
    }
}
