package com.vesta.exception.handler;

import com.vesta.exception.VestaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(VestaException.class)
    public void handleVestaException(HttpServletResponse res, VestaException ex) throws IOException {
        log.error(ex.getMessage(), ex);
        res.sendError(ex.getHttpStatus().value(), ex.getMessage());
    }

    @ExceptionHandler({MessagingException.class})
    public void handleMessagingException(HttpServletResponse res, Exception ex) throws IOException {
        log.error(ex.getMessage(), ex);
        res.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void handlePasswordException(HttpServletResponse res, Exception ex) throws IOException {
        log.error(ex.getMessage(), ex);
        res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
