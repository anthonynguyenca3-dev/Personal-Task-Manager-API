package com.anthonynguyen.project.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor((Decoders.BASE64.decode(secret)));

    }

    public String generateToken(Long userId) {
        return Jwts.builder().subject(userId.toString()).issuedAt(new Date()).expiration(new Date((System.currentTimeMillis() + 3600000))).signWith(getSigningKey()).compact();
    }

    public boolean isTokenValid(String token) {

        try {

            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);

            return true;

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }

    }

}
