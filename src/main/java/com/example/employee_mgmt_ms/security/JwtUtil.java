package com.example.employee_mgmt_ms.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Utility class to generate/validate JWT tokens.
 */
@Component
public class JwtUtil {
    // Implementation for JWT generation and validation would go here
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpirationInMs;

    // Methods for generating and validating JWT tokens would be implemented here
    public String generateToken(String username) {
        // Implementation for generating JWT token
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        // Build the token
        return Jwts.builder()
                .setSubject(username)                               // Set the subject as the username
                .setIssuedAt(now)                                   // Set the issued at claim
                .setExpiration(expiryDate)                          // Set the expiration claim
                .signWith(SignatureAlgorithm.HS512, jwtSecret)      // Sign the token with the secret key
                .compact();                                         // Compact the token into a string
    }

    /**
     * Extract username (subject) from token.
     */
    public String getUsernameFromToken(String token) {
       return parseClaims(token).getSubject();
    }

    /**
     * Validate token signature & expiry.
     */
    public boolean  validateToken(String token) {
        try {
            Claims claims = parseClaims(token);
            Date expiration = claims.getExpiration();
            return !expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
//        try {
//            parseClaims(token);
//            return true;
//        } catch (ExpiredJwtException e) {
//            // token expired
//        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
//            // invalid token
//        }
//        return false;
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)                           // Set the signing key
                .parseClaimsJws(token)                              // Parse the token
                .getBody();                                         // Get the body of the token
    }

}
