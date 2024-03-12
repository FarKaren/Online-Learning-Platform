package org.otus.platform.gateway.service.userservice.auth.refreshtoken;

import org.otus.platform.gateway.dto.user.auth.refresh.RefreshTokenRequest;
import org.otus.platform.gateway.dto.user.auth.refresh.RefreshTokenResponse;
import org.otus.platform.gateway.security.user.CustomUserDetails;

public interface RefreshTokenService {
    RefreshTokenResponse refreshToken(RefreshTokenRequest request, CustomUserDetails details);
}
