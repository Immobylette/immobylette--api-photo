package com.immobylette.api.photo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthMissingException extends RuntimeException{
    public AuthMissingException() {
        super("Authorization header is missing");
    }
}
