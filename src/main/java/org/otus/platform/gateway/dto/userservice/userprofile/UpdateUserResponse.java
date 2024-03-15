package org.otus.platform.gateway.dto.userservice.userprofile;

import lombok.Builder;
import org.otus.platform.gateway.security.user.UserRole;

import java.util.UUID;

@Builder
public record UpdateUserResponse(
        UUID id,
        String name,
        String email,
        String phone,
        UserRole role
) {
}
