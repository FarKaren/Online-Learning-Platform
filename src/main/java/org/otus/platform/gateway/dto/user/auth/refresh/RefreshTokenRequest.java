package org.otus.platform.gateway.dto.user.auth.refresh;

import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(
        @NotNull
        String token
) {
}
