package com.example.BookStoreFinalProject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j
public class JwtUtils {

    private static final long SIX_MONTHS_IN_MS = 100L * 60 * 60 * 24 * 30 * 6;

    private SecretKey signingKey;

    @Value("${jwt.secret}")
    private String rawSecret;

    @PostConstruct
    private void initializeKey() {
        byte[] keyBytes = rawSecret.getBytes(StandardCharsets.UTF_8);
        this.signingKey = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public String createToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + SIX_MONTHS_IN_MS))
                .signWith(signingKey)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String tokenUsername = extractUsername(token);
        return tokenUsername.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload());
    }
}
