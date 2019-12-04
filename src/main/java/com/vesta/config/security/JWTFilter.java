package com.vesta.config.security;

import com.vesta.service.UserService;
import com.vesta.service.dto.AuthenticationCredential;
import com.vesta.service.dto.RoleDto;
import com.vesta.service.dto.UserDto;
import com.vesta.service.impl.RolesServiceImpl;
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

    @Autowired
    private UserService userService;

    @Autowired
    private RolesServiceImpl rolesService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        String subject = tokenService.getSubject(request);
        //new code payload
        Map <String, String> payload = tokenService.getPayload(request);

        //old case subject
        if (subject != null) {
            UserDto userDto = userService.getByUsername(subject);
            //what?
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(converter(userDto));
        }
        //what?
        filterChain.doFilter(request, response);

        //code for payload scenario
        //for correct work was created class RoleConverter
        if (payload != null) {
            //how jwt will set keys for map?
            UserDto userDto = userService.getByUsername(payload.get("username"));
            RoleDto roleDto = rolesService.findDtoByName(payload.get("roles"));
            //sets flas isAuthentificated in system
            SecurityContextHolder.getContext().setAuthentication(converter(userDto));
        }
    }

    //setting authentificated flag for its writing in system
    private AuthenticationCredential converter(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new AuthenticationCredential(userDto, true);
    }
}