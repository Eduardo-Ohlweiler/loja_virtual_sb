package com.loja_virtual_sb.security;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTTokenAutenticacaoService {
    private static final long EXPIRATION_TIME = 959990000;
    private static final String SECRET = "ss/-*-*sds565dsd-s/d-s*dsds";

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public void addAuthentication(HttpServletResponse response, String username) throws Exception {
        String JWT = Jwts.builder()
                     .setSubject(username)
                     .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                     .signWith(key, SignatureAlgorithm.HS512)
                     .compact();

        String token = TOKEN_PREFIX + " " + JWT;
        response.addHeader(HEADER_STRING, token);

        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
    }
}
