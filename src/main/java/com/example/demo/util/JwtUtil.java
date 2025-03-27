// JwtUtil.java
package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private String secret = "your_secret_key";

    public String generateToken(String username, boolean rememberMe) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username, rememberMe);
    }

    public String generateRefreshToken(String username, boolean rememberMe) {
        Map<String, Object> claims = new HashMap<>();
        long expirationTime = rememberMe ? 30L * 24 * 60 * 60 * 1000 : 7L * 24 * 60 * 60 * 1000; // 60 days : 7 days
        return createToken(claims, username, expirationTime);
    }

    private String createToken(Map<String, Object> claims, String subject, boolean rememberMe) {
        long expirationTime = rememberMe ? 30L * 24 * 60 * 60 * 1000 : 60L * 60 * 1000; // 30 days : 60 minutes
        return createToken(claims, subject, expirationTime); // 60 minutes
    }

    private String createToken(Map<String, Object> claims, String subject, long expirationTime) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String validateToken(String token) {
        if (!isTokenExpired(token)) {
            return extractUsername(token);
        }
        return null;
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}