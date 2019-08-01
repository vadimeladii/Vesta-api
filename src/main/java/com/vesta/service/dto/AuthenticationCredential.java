package com.vesta.service.dto;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AuthenticationCredential implements Authentication {

    private UserDto dto;
    private boolean isAuthenticated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return dto.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role)))
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return this.dto;
    }

    @Override
    public Object getPrincipal() {
        return this.dto;
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return this.dto.getUsername();
    }
}
