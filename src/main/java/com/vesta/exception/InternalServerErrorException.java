package com.vesta.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends RuntimeException {

    private static final long serialVersionUID = 4637632259959692060L;

    private final String message;

    public InternalServerErrorException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
