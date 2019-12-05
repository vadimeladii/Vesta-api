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

    // methods for payload. After delete of previous version, their names will be changed

    Map<String, List<String>> getPayload(HttpServletRequest request);

    Map<String, List<String>> getPayload(String token);

    Map<String, List<String>> getRefreshPayload(String token);

    Token generatePayloadAccessToken(String username, List<String> roles);

    Token generatePayloadRefreshToken(String username, List<String> roles);

    Token generatePayloadEmailToken(String username, List<String> roles);
}
