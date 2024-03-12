package org.otus.platform.gateway.security.jwt;

import org.otus.platform.gateway.security.user.UserRole;

import java.util.Set;
import java.util.UUID;

public record JwtObject(
    UUID subject,
    Set<UserRole> roles
) {
}
