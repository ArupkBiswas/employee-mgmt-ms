package com.example.employee_mgmt_ms.security;


import com.example.employee_mgmt_ms.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility class to generate/validate JWT tokens.
 */
@Component
public class JwtUtil {
//    @Value("${jwt.secret}")
//    private String secret;
    private final JwtConfig jwtConfig;

    // NOTE: application.yml uses `jwt.expiration` (milliseconds)
    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    private SecretKey getSigningKey() {
        // Prefer a Base64-encoded secret (recommended). If the configured secret is plain text,
        // fall back to the UTF-8 bytes. Then validate the minimum required key length for the
        // algorithm (HS512 requires 512 bits / 64 bytes).
        byte[] keyBytes = null;
        if (jwtConfig.getSecret() == null) {
            throw new IllegalStateException("JWT secret is not configured (property 'jwt.secret').");
        }
        // Try Base64 decode first (if user stored a base64 key)
        try {
            byte[] decoded = java.util.Base64.getDecoder().decode(jwtConfig.getSecret());
            if (decoded != null && decoded.length > 0) {
                keyBytes = decoded;
            }
        } catch (IllegalArgumentException ignored) {
            // not a base64 string; we'll use raw bytes below
        }

        if (keyBytes == null) {
            keyBytes = jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8);
        }

        int minBytes = SignatureAlgorithm.HS512.getMinKeyLength() / 8; // bytes
        if (keyBytes.length < minBytes) {
            throw new IllegalStateException("The configured JWT secret is too short for "
                    + SignatureAlgorithm.HS512.name() + ". Minimum required key size is " + minBytes + " bytes ("
                    + (minBytes * 8) + " bits). Provide a longer secret or a Base64-encoded key. "
                    + "You can generate a secure key with: 'openssl rand -base64 64' or using Keys.secretKeyFor(SignatureAlgorithm.HS512) and storing its Base64.");
        }

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username, Long userId, String role) {
        Map<String, Object> claims = Map.of(
                "userId", userId,
                "role", role
        );
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                // signWith(key, algorithm) - key first
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // Extract username (subject)
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Convenience method expected by existing code
    public String getUsernameFromToken(String token) {
        return extractUsername(token);
    }

    // Generic claim extractor
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Convenience method expected by existing code
    public boolean validateToken(String token) {
        try {
            String username = getUsernameFromToken(token);
            return (username != null && !isTokenExpired(token));
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isTokenValid(String token, String username) {
        return (extractUsername(token).equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
