package ccy.civilizationleaderboard.config.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    boolean isTokenExpired(String token);
    Date extractExpiration(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    Claims extractPayload(String token);
    String generateToken(UserDetails userDetails);
    String generateToken(UserDetails userDetails, Map<String, Object> extraClaims);
}
