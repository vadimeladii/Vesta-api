package com.vesta.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 8584511077473268924L;

    private final String message;

    public BadRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}


