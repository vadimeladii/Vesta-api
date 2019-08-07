package com.vesta.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vesta.exception.view.ErrorData;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
//        ErrorData response = ErrorData.builder()
//                .status(HttpStatus.UNAUTHORIZED.value())
//                .message("Unauthorised")
//                .timestamp(new Date())
//                .error("Unauthorised")
//                .build();
//        OutputStream out = httpServletResponse.getOutputStream();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(out, response);
//        out.flush();
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,  "Unauthorized");
    }
}
