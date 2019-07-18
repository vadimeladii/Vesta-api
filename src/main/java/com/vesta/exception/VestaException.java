package com.vesta.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Slf4j
public class VestaException extends RuntimeException {

    private final String message;

    private final HttpStatus httpStatus;

    VestaException(String message, HttpStatus httpStatus) {
        super(message);
        log.error(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
