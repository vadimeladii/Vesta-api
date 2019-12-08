package com.vesta.service;

import com.vesta.controller.view.Token;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface TokenService {
    Map<String, List<String>> getPayload(HttpServletRequest request);

    Map<String, List<String>> getPayload(String token);

    Map<String, List<String>> getRefreshPayload(String token);

    Token generateAccessToken(String username, List<String> roles);

    Token generateRefreshToken(String username, List<String> roles);
    
    Token generateEmailToken(String username);
}
