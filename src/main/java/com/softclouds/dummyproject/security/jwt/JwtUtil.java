package com.softclouds.dummyproject.security.jwt;

import com.softclouds.dummyproject.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    //    @Value("${jwt.tokenValidity}")
    private final long JWT_TOKEN_VALIDITY_IN_MS = 5 * 60 * 60 * 1000;
    //    @Value("${jwt.secretKey}")
    private final String SECRET_KEY = "apple";

    public String generateJwtToken(Authentication authentication) {
        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY_IN_MS))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT Signature : {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT Token  : {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("JWT token is Expired: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("JWT token is unsupported: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims String is Empty: {}", ex.getMessage());
        }
        return false;
    }
}
