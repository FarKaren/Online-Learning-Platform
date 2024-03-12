package org.otus.platform.gateway.dto.user.auth.login;

public record LoginResponse(String accessToken, String refreshToken) {
}
