package com.vesta.service.impl;

import com.vesta.controller.view.Token;
import com.vesta.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.vesta.config.security.SecurityConstants.*;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${vesta.access.expiration}")
    private Long accessExpiration;

    @Value("${vesta.refresh.expiration}")
    private Long refreshExpiration;
  
    @Value("${vesta.email.expiration}")
    private Long emailExpiration;

    public Token generatedAccessToken(String username) {
        return buildToken(username, accessExpiration, JWT_SECRET, TOKEN_PREFIX);
    }

    public Token generatedRefreshToken(String username) {
        return buildToken(username, refreshExpiration, REFRESH_SECRET, TOKEN_PREFIX);
    }

    public Token generatedEmailToken(String username) {
        return buildToken(username, emailExpiration, EMAIL_SECRET, "");
    }

    @Override
    public String getSubject(String token) {
        return buildSubject(token, EMAIL_SECRET);
    }

    public String getSubject(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);

        return buildSubject(token, JWT_SECRET);
    }

    public String getRefreshSubject(String token) {
        return buildSubject(token, REFRESH_SECRET);
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

    private Token buildToken(String username, Long expirationTime, String secret, String prefix) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return new Token(prefix + JWT);
    }
}