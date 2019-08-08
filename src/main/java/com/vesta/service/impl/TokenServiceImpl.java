package com.vesta.service.impl;

import com.vesta.controller.view.Token;
import com.vesta.service.TokenService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.vesta.config.security.SecurityConstants.*;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${vesta.access.expiration}")
    private Long accessExpiration;

    @Value("${vesta.refresh.expiration}")
    private Long refreshExpiration;

    @Value("${vesta.email.expiration}")
    private Long emailExpiration;

    @Override
    public Token generatedAccessToken(String username) {
        log.info("method --- generatedAccessToken");

        return buildToken(username, accessExpiration, JWT_SECRET, TOKEN_PREFIX);
    }

    @Override
    public Token generatedRefreshToken(String username) {
        log.info("method --- generatedRefreshToken");

        return buildToken(username, refreshExpiration, REFRESH_SECRET, TOKEN_PREFIX);
    }

    @Override
    public Token generatedEmailToken(String username) {
        log.info("method --- generatedEmailToken");

        return buildToken(username, emailExpiration, EMAIL_SECRET, "");
    }

    @Override
    public String getSubject(String token) {
        return buildSubject(token, EMAIL_SECRET);
    }

    @Override
    public String getSubject(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);

        return buildSubject(token, JWT_SECRET);
    }

    @Override
    public String getRefreshSubject(String token) {
        log.info("method --- getRefreshSubject");

        return buildSubject(token, REFRESH_SECRET);
    }

    private String buildSubject(String token, String secret) {
        if (token != null) {
            try {
                return Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();
            } catch (SignatureException e) {
                log.error("signature exception" + e);

            } catch (MalformedJwtException e) {
                log.error("token malformed" + e);

            } catch (ExpiredJwtException e) {
                log.error("token expired" + e);

            } catch (UnsupportedJwtException e) {
                log.error("unsupported" + e);

            } catch (IllegalArgumentException e) {
                log.error("Illegal" + e);
            }
        }
        return null;
    }

    private Token buildToken(String username, Long expirationTime, String secret, String prefix) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return new Token(prefix + jwt);
    }
}