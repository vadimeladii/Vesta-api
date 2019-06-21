package com.vesta.config.security;

import com.vesta.service.UserService;
import com.vesta.service.dto.AuthentificationCredential;
import com.vesta.service.dto.UserDto;
import com.vesta.service.impl.TokenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
public class JWTFilter extends GenericFilter {

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String subject = tokenService.getSubject((HttpServletRequest) servletRequest);

        if (subject != null) {
            try {
                UserDto userDto = userService.getByUsername(subject);
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(converter(userDto));

            } catch (Exception e) {
                log.error("Can't authentificate user", e);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private AuthentificationCredential converter(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new AuthentificationCredential(userDto, true);
    }
}