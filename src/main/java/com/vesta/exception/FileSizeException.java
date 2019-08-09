package com.vesta.exception;

import org.springframework.http.HttpStatus;

public class FileSizeException extends VestaException {

    private static final long serialVersionUID = 6744092433398204237L;

    public FileSizeException(String message) {
        super(message, HttpStatus.NOT_ACCEPTABLE);
    }

}
