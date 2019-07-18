package com.vesta.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends VestaException {

    private static final long serialVersionUID = -37743974332454250L;

    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
