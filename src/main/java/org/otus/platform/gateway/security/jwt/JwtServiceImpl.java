package org.otus.platform.gateway.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.otus.platform.gateway.security.user.UserRole;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtConfigProperties jwtConfigProperties;

    @Override
    public JwtObject extractData(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfigProperties.key()));
        Claims body = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        UUID subject = UUID.fromString(body.getSubject());

        List<UserRole> roles = body.get("roles", List.class).stream()
                .filter(role -> role instanceof String)
                .map(role -> UserRole.valueOf((String) role))
                .toList();


        return new JwtObject(subject, new HashSet<>(roles));
    }

    @Override
    public boolean validateAccessToken(String token) {
        return validateToken(token, jwtConfigProperties.key());
    }

    @Override
    public boolean validateRefreshToken(String token) {
        return validateToken(token, jwtConfigProperties.refreshKey());
    }

    @Override
    public String generateAccessToken(UUID id, UserRole role, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("roles", Collections.singleton(role));
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfigProperties.key()));
        Date now = new Date();

        return Jwts.builder()
                .signWith(key)
                .setSubject(String.valueOf(id))
                .setIssuedAt(now)
                .setAudience(jwtConfigProperties.audience())
                .setIssuer(jwtConfigProperties.issuer())
                .setExpiration(
                        new Date(now.getTime() + TimeUnit.SECONDS.toMillis(jwtConfigProperties.expirationSec() != null
                                ? jwtConfigProperties.expirationSec() : 0)))
                .addClaims(claims)
                .compact();
    }

    @Override
    public String generateRefreshToken() {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfigProperties.refreshKey()));
        Date now = new Date();

        return Jwts.builder()
                .signWith(key)
                .setExpiration(
                        new Date(now.getTime() + TimeUnit.SECONDS.toMillis(jwtConfigProperties.refreshExpirationSec() != null ?
                                jwtConfigProperties.refreshExpirationSec() : 0)))
                .compact();
    }

    private boolean validateToken(String token, String propertyKey) {
        try {
            Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(propertyKey));
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }


}
