package org.otus.platform.gateway.security.jwt;

import org.otus.platform.gateway.security.user.UserRole;

import java.util.UUID;

public interface JwtService {
    JwtObject extractData(String token);
    boolean validateAccessToken(String token);
    boolean validateRefreshToken(String token);
    String generateAccessToken(UUID id, UserRole role, String username);
    String generateRefreshToken();
}
