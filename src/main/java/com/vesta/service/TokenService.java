package com.vesta.service;

import com.vesta.controller.view.Token;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    Token generatedAccessToken(String username);

    String getSubject(HttpServletRequest request);

    Token generatedRefreshToken(String username);

    String getRefreshSubject(String token);

    Token generatedEmailToken(String username);

    String getSubject(String token);
}
