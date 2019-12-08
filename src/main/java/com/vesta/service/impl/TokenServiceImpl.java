package com.vesta.service.impl;

import com.vesta.controller.view.Token;
import com.vesta.service.TokenService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    //-------------------------- my methods ---------------------------------------
    @Override
    public Token generateAccessToken(String username, List<String> roles) {
        log.info("method --- generatePayloadAccessToken");

        return buildPayloadToken(username, roles, accessExpiration, JWT_SECRET, TOKEN_PREFIX);
    }

    @Override
    public Token generateRefreshToken(String username, List<String> roles) {
        log.info("method --- generatePayloadRefreshToken");

        return buildPayloadToken(username, roles, refreshExpiration, REFRESH_SECRET, TOKEN_PREFIX);
    }

    @Override
    public Token generateEmailToken(String username) {
        log.info("method --- generateEmailToken");

        return buildEmailToken(username, emailExpiration, EMAIL_SECRET, "");
    }

    @Override
    public Map<String, List<String>> getPayload(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);

        return buildPayload(token, JWT_SECRET);
    }

    @Override
    public Map<String, List<String>> getPayload(String token) {
        log.info("method --- getPayload");

        return buildPayload(token, EMAIL_SECRET);
    }

    @Override
    public Map<String, List<String>> getRefreshPayload (String token) {
        log.info("method --- getRefreshPayload");

        return buildPayload(token, REFRESH_SECRET);
    }

    private Map<String, List<String>> buildPayload(String token, String secret) {
        if(token != null) {
            try{
                String[] output = Jwts.parser()
                                .setSigningKey(secret)
                                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                                .getBody()
                                .getSubject()
                                .split(":");

                Map<String, List<String>> credentials = new HashMap<>();
                credentials.put("user", Collections.singletonList(output[0]));
                credentials.put("roles", Arrays.asList(output[1].split(",")));
                return credentials;
            }
            catch (SignatureException e) {
                log.error("signature exception" + e);
            }
            catch (MalformedJwtException e) {
                log.error("token malformed" + e);
            }
            catch (ExpiredJwtException e) {
                log.error("token expired" + e);
            }
            catch (UnsupportedJwtException e) {
                log.error("unsupported" + e);
            }
            catch (IllegalArgumentException e) {
                log.error("Illegal" + e);
            }
        }
        return null;
    }

    private Token buildPayloadToken(String username, List<String> roles, Long expirationTime, String secret, String prefix) {
        String jwt = Jwts.builder()
                .setSubject(username + ":" + String.join(",", roles))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        return new Token(prefix + jwt);
    }

    private Token buildEmailToken(String username, Long expirationTime, String secret, String prefix) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        return new Token(prefix + jwt);
    }
}