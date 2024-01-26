package com.gestion.formation.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

public class TokenUtil {

    //private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static SecretKey key = Jwts.SIG.HS256.key().build();
    private static final long expirationTimeInMillis = 3600000; // 1 heure

    public static String generateToken(String email) {
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeInMillis);
        return Jwts.builder()
                .subject(email)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }
    
    //public static boolean validateToken(String token) {
    public static boolean validateToken(String token, String email) {
        try {
            //Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject().equals(email);
        } catch (Exception e) {
            return false;
        }
    }

}

