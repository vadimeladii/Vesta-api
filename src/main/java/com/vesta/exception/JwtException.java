package com.vesta.exception;

import org.springframework.http.HttpStatus;

public class JwtException extends VestaException {

    private static final long serialVersionUID = -8362067667595315126L;

    public JwtException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

}
