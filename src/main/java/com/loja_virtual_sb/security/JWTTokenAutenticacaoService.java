package com.loja_virtual_sb.security;

import java.security.Key;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.loja_virtual_sb.ApplicationContextLoad;
import com.loja_virtual_sb.model.Usuario;
import com.loja_virtual_sb.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTTokenAutenticacaoService {
    private static final long EXPIRATION_TIME = 959990000;
    private static final String SECRET = "super-secret-key-that-is-at-least-64-bytes-long-to-meet-requirements-1234567890";

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
        liberacaoCors(response);

        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
    }

    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader(HEADER_STRING);
        if(token != null) {
            String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();

            String user = Jwts.parserBuilder().
                               setSigningKey(key).
                               build().
                               parseClaimsJws(tokenLimpo).
                               getBody().
                               getSubject();

            if(user != null) {
                Usuario usuario = ApplicationContextLoad.
                                    getApplicationContext().
                                    getBean(UsuarioRepository.class).
                                    findUserByLogin(token);

                if (usuario != null) {
                    return new UsernamePasswordAuthenticationToken(usuario.getLogin(),
                                                                   usuario.getSenha(),
                                                                   usuario.getAuthorities());
                }
            }
        }

        liberacaoCors(response);

        return null;
    }

    public void liberacaoCors(HttpServletResponse response){
        if(response.getHeader("Access-Control_Allow-Origin") == null){
            response.addHeader("Access-Control-Allow-Origin", "*");
        }
        if(response.getHeader("Access-Control_Allow-Headers") == null){
            response.addHeader("Access-Control-Allow-Headers", "*");
        }
        if(response.getHeader("Access-Control_Request-Origin") == null){
            response.addHeader("Access-Control-Request-Origin", "*");
        }
        if(response.getHeader("Access-Control_Allow-Methods") == null){
            response.addHeader("Access-Control-Allow-Methods", "*");
        }
    }
}
