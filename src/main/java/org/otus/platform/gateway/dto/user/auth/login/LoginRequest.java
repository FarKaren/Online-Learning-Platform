package org.otus.platform.gateway.dto.user.auth.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotNull
        @Email
        @Size(max = 100)
        String email,

        @NotNull
        @Size(max = 100)
        String password
) {

}
