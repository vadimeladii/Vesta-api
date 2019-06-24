package com.vesta.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends VestaException {

    private static final long serialVersionUID = 4637632259959692060L;

    public InternalServerErrorException(String message){
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
