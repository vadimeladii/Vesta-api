package com.vesta.config.security;

import com.vesta.service.dto.AuthenticationCredential;
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
import java.util.Arrays;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private TokenServiceImpl tokenService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        String subject = tokenService.getSubject(request);

        if (subject != null) {
            String[] lines = subject.split(":");
            String username = lines[0];
            String[] roles = lines[1].split(",");

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(converter(username, roles));
        }
        filterChain.doFilter(request, response);
    }

    private AuthenticationCredential converter(String username, String[] roles) {
        if (username == null)
            return null;
        if (roles == null)
            return null;
        return new AuthenticationCredential(username, Arrays.asList(roles), true);
    }
}