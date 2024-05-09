package com.immobylette.api.photo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GCPStorageException extends RuntimeException{
    public GCPStorageException(String message){
        super(message);
    }
}
