package org.otus.platform.gateway.controller.userservice.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.otus.platform.gateway.dto.user.auth.refresh.RefreshTokenRequest;
import org.otus.platform.gateway.dto.user.auth.refresh.RefreshTokenResponse;
import org.otus.platform.gateway.security.user.CustomUserDetails;
import org.otus.platform.gateway.service.userservice.auth.refreshtoken.RefreshTokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/refresh")
@Validated
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    @PostMapping
    ResponseEntity<RefreshTokenResponse> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request,
            Authentication authentication
    ) {
        var jwtResponse =
                refreshTokenService.refreshToken(request, (CustomUserDetails) authentication.getPrincipal());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtResponse.accessToken())
                .body(jwtResponse);
    }
}
