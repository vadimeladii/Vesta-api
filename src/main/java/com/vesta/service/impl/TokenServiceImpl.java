package com.vesta.service.impl;

import com.vesta.controller.view.Token;
import com.vesta.service.TokenService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

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

    //standard access tokens///////////////////////
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
    //////////////////////////////////////////////////

    //-------------------------- my methods ---------------------------------------
    @Override
    public Token generatePayloadAccessToken(String username, List<String> roles) {
        log.info("method --- generatePayloadAccessToken");

        return buildPayloadToken(username, roles, accessExpiration, JWT_SECRET, TOKEN_PREFIX);
    }

    @Override
    public Token generatePayloadRefreshToken(String username, List<String> roles) {
        log.info("method --- generatePayloadRefreshToken");

        return buildPayloadToken(username, roles, refreshExpiration, REFRESH_SECRET, TOKEN_PREFIX);
    }

    @Override
    public Token generatePayloadEmailToken(String username, List<String> roles) {
        log.info("method --- generatePayloadEmailToken");

        return buildPayloadToken(username, roles, emailExpiration, EMAIL_SECRET, "");
    }
    //-----------------------------------------------------------------------------

    //standard token getter///////////////////////////////////////////////////
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
    //////////////////////////////////////////////////////////////////////////

    //-------------------- my methods --------------------------------------

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

    ////////////////////////////////////////////////////////////////

    //possible working prototype for creation of payload for token. Check how we can decode it.
    private Map<String, List<String>> buildPayload(String token, String secret) {
        //if there is any token to check from
        if(token != null) {
            try{
                String[] output = Jwts.parser()
                                .setSigningKey(secret)
                                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                                .getBody()
                                .getSubject()
                                .split(":");

                Map<String, List<String>> credentials = new HashMap<String, List<String>>();
                credentials.put("user", Arrays.asList(output[0]));
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

    private String buildSubject(String token, String secret) {
        if (token != null) {
            try {
                return Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    // building tokens

    private Token buildPayloadToken(String username, List<String> roles, Long expirationTime, String secret, String prefix) {
        String jwt = Jwts.builder()
                .setSubject(username + ":" + String.join(",", roles))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        return new Token(prefix + jwt);
    }

    private Token buildToken(String username, Long expirationTime, String secret, String prefix) {
        String jwt = Jwts.builder()
                .setSubject(username)   //set encoding data
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret) //setting secret key
                .compact();
        return new Token(prefix + jwt);
    }
}