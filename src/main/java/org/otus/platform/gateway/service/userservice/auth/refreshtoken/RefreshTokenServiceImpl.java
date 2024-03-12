package org.otus.platform.gateway.service.userservice.auth.refreshtoken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.otus.platform.gateway.dto.user.auth.refresh.RefreshTokenRequest;
import org.otus.platform.gateway.dto.user.auth.refresh.RefreshTokenResponse;
import org.otus.platform.gateway.exception.exceptions.AuthenticationFailedException;
import org.otus.platform.gateway.security.jwt.JwtService;
import org.otus.platform.gateway.security.user.CustomUserDetails;
import org.otus.platform.gateway.security.user.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final JwtService jwtService;

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request, CustomUserDetails details) {
        log.info("invoke refreshToken() method");
        if (jwtService.validateRefreshToken(request.token())) {
            var accessToken = jwtService.generateAccessToken(
                    details.id(),
                    details.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .findFirst()
                            .map(UserRole::valueOf)
                            .get(),
                    details.getUsername()
            );
            var refreshToken = jwtService.generateRefreshToken();
            return new RefreshTokenResponse(accessToken, refreshToken);
        } else {
            throw new AuthenticationFailedException("Invalid refresh token");
        }
    }
}
