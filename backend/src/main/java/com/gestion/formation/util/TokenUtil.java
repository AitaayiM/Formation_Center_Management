package com.gestion.formation.util;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

public class TokenUtil {

    private static SecretKey key = Jwts.SIG.HS256.key().build();
    private static final long expirationTimeInMillis = 86400000; // 1 day

    public static String generateToken(String email) {
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeInMillis);
        return Jwts.builder()
                .subject(email)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }
    
    public static boolean validateToken(String token, String email) {
        try {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject().equals(email);
        } catch (Exception e) {
            return false;
        }
    }

    public static String extractToken(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

}

