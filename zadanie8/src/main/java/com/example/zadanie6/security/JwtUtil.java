package com.example.zadanie6.security;

import com.example.zadanie6.users.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final String SECRET = "kluczkluczkluczkluczkluczkluczkluczklucz";

    public String generateToken(String username, Set<Role> roles) {
        String rolesString = roles.stream()
                .map(Role::getName)
                .collect(Collectors.joining(",")); // np. "USER,ADMIN"

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", rolesString)  // ← ZAPIS RÓL W TOKENIE
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1h ważności
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractRoles(String token) {
        return (String) extractAllClaims(token).get("roles");
    }
}
