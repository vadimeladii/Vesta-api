package com.vesta.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends VestaException {

    private static final long serialVersionUID = 5433844894638566965L;

    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
