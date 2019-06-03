package com.vesta.service.dto;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AuthentificationCredential implements Authentication {

    private UserDto userDto;
    private boolean isAuthenticated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDto.getRoles()
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
        return this.userDto;
    }

    @Override
    public Object getPrincipal() {
        return this.userDto;
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return this.userDto.getUsername();
    }
}
