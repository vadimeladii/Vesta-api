package com.vesta.exception.handler;

import com.vesta.exception.JwtException;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        Throwable throwable = getError(webRequest);
        if (throwable != null) {
            if (throwable instanceof JwtException) {
                HttpStatus httpStatus = ((JwtException) throwable).getHttpStatus();
                errorAttributes.put("status", httpStatus.value());
                errorAttributes.put("error", httpStatus.getReasonPhrase());
            }
        }
        return errorAttributes;
    }
}
