package org.otus.platform.gateway.dto.userservice.auth.register;

public record RegistrationResponse(String accessToken, String refreshToken) {
}
