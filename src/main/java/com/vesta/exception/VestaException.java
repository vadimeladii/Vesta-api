package com.vesta.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class VestaException extends RuntimeException {

    private final String message;

    private final HttpStatus httpStatus;

    VestaException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
