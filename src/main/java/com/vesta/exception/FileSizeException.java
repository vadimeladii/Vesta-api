package com.vesta.exception;

import org.springframework.http.HttpStatus;

public class FileSizeException extends VestaException {

    private static final long serialVersionUID = -37743974332454250L;

    public FileSizeException(String message) {
        super(message, HttpStatus.NOT_ACCEPTABLE);
    }
}
