package com.borajunto.projetobora.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationInMs;

    public String gerarToken(String login) {
        Date agora = new Date();
        Date dataExpiracao = new Date(agora.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(agora)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String obterLoginDoToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
