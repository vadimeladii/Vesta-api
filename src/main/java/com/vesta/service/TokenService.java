package com.vesta.service;

import com.vesta.controller.view.Token;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface TokenService {

    Token generatedAccessToken(String username);

    String getSubject(HttpServletRequest request);

    Token generatedRefreshToken(String username);

    String getRefreshSubject(String token);

    Token generatedEmailToken(String username);

    String getSubject(String token);

    Map<String, String> getPayload(HttpServletRequest request);

    Map<String, String> getPayload(String token);

    Map<String, String> getRefreshPayload(String token);

    Token generatePayloadAccessToken(String username, List<String> roles);

    Token generatePayloadRefreshToken(String username, List<String> roles);
}
