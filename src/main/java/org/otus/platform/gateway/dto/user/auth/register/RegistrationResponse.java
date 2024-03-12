package org.otus.platform.gateway.dto.user.auth.register;

public record RegistrationResponse(String accessToken, String refreshToken) {
}
