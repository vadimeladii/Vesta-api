package com.vesta.service.impl;

import com.vesta.controller.view.Token;
import com.vesta.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

import static com.vesta.config.security.SecurityConstants.*;

@Service
public class TokenServiceImpl implements TokenService {


    public Token generatedAccessToken(String username) {
        return buildToken(username, EXPIRATION_TIME, JWT_SECRET);
    }

    public Token generatedRefreshToken(String username) {
        return buildToken(username, REFRESH_EXPIRATION, REFRESH_SECRET);
    }

    public Token generatedEmailToken(String username) {
        return buildToken(username, EMAIL_EXPIRATION, EMAIL_SECRET);
    }

    public String getSubject(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);

        return buildSubject(token, JWT_SECRET);
    }

    public String getRefreshSubject(String token) {
        return buildSubject(token, REFRESH_SECRET);
    }

    public String getEmailTokenSubject(String token){
        return buildSubject(token, EMAIL_SECRET);
    }

    private String buildSubject(String token, String secret) {
        if (token != null) {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
        }
        return null;
    }

    private Token buildToken(String username, Long expirationTime, String secret) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return new Token(TOKEN_PREFIX + JWT);
    }
}