package com.vesta.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends VestaException {

    private static final long serialVersionUID = -8362067667595315176L;

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

