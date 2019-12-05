package com.vesta.config.security;

import com.vesta.exception.ConflictException;
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
import java.util.List;
import java.util.Map;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private TokenServiceImpl tokenService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        Map<String, List<String>> subject = tokenService.getPayload(request);

        if (subject != null) {
            if(subject.get("user").size() > 1)
                throw new ConflictException("There can't be more than one user");

            String username = subject.get("user").get(0);
            List<String> roles = subject.get("roles");

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(converter(username, roles));
        }
        filterChain.doFilter(request, response);
    }

    private AuthenticationCredential converter(String username, List<String> roles) {
        if (username == null)
            return null;
        if (roles == null)
            return null;
        return new AuthenticationCredential(username, roles, true);
    }
}