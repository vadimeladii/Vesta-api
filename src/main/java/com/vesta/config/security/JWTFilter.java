package com.vesta.config.security;

import com.vesta.service.UserService;
import com.vesta.service.dto.AuthenticationCredential;
import com.vesta.service.dto.UserDto;
import com.vesta.service.impl.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private UserService userService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        String subject = tokenService.getSubject(request);

        if (subject != null) {
            UserDto userDto = userService.getByUsername(subject);
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(converter(userDto));
        }
        filterChain.doFilter(request, response);
    }

    private AuthenticationCredential converter(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new AuthenticationCredential(userDto, true);
    }
}