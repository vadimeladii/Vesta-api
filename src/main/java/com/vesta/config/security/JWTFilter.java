package com.vesta.config.security;

import com.vesta.service.TokenService;
import com.vesta.service.UserService;
import com.vesta.service.dto.AuthentificationCredential;
import com.vesta.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JWTFilter extends GenericFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String subject = tokenService.getSubject((HttpServletRequest) servletRequest);
        UserDto userDto = userService.getByUsername(subject);


        SecurityContextHolder
                .getContext()
                .setAuthentication(converter(userDto));

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private AuthentificationCredential converter(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new AuthentificationCredential(userDto, true, userDto.getUsername());
    }
}