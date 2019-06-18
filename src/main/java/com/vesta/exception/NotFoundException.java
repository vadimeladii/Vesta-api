package com.vesta.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8362067667595315176L;

    private final String message;

    public NotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}

