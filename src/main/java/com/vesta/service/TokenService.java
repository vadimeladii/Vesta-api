package com.vesta.service;

import com.vesta.controller.view.Token;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    Token generatedToken(String username);

    String getSubject(HttpServletRequest request);
}
