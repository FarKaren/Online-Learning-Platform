package org.otus.platform.gateway.dto.userservice.auth.refresh;

import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(
        @NotNull
        String token
) {
}
