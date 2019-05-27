package com.vesta.services;

import com.vesta.controller.view.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.vesta.config.security.SecurityConstants.*;

@Service
public class TokenService {

    public Token generatedToken(String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
        return new Token(JWT);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);

        if (token != null) {
            String username = Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            if (username != null) // we managed to retrieve a user
            {
                return null; //new AuthenticatedUser(username);
            }
        }
        return null;
    }
}