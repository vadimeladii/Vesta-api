package com.vesta.exception;

import org.springframework.http.HttpStatus;

public class JsonException extends VestaException {

    private static final long serialVersionUID = -2755197910442034817L;

    public JsonException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
