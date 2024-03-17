package org.otus.platform.gateway.dto.userservice.auth.register;

import lombok.Builder;

@Builder
public record CreateUserRequest(
        String name,
        String email,
        String phone,
        String password
) {
}
