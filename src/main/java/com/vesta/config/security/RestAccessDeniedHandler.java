package com.vesta.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vesta.exception.view.ErrorData;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private static final String ACTION = "Acces Denied";

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        ErrorData response = ErrorData.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(ACTION)
                .timestamp(new Date())
                .error(ACTION)
                .build();
        response.setMessage(ACTION);
        OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, response);
        out.flush();
    }
}