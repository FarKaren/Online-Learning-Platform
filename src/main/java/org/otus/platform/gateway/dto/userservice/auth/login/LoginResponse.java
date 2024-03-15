package org.otus.platform.gateway.dto.userservice.auth.login;

public record LoginResponse(String accessToken, String refreshToken) {
}
