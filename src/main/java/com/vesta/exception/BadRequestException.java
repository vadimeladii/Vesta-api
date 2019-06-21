package com.vesta.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends VestaException {

    private static final long serialVersionUID = 8584511077473268924L;

    public BadRequestException(String message){
        super(message, HttpStatus.BAD_REQUEST );
    }
}


