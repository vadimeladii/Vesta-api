package com.vesta.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class    GlobalExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public void handleCustomException(HttpServletResponse res, NotFoundException ex) throws IOException {
        res.sendError(ex.getHttpStatus().value(), ex.getMessage());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public void handleException(HttpServletResponse res, InternalServerErrorException ex) throws IOException {
        res.sendError(ex.getHttpStatus().value(), ex.getMessage());
    }
}
