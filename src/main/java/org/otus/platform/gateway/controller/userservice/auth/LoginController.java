package org.otus.platform.gateway.controller.userservice.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.otus.platform.gateway.dto.user.auth.login.LoginRequest;
import org.otus.platform.gateway.dto.user.auth.login.LoginResponse;
import org.otus.platform.gateway.service.userservice.auth.login.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/login")
@Validated
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    ResponseEntity<LoginResponse> loginByEmail(@Valid @RequestBody LoginRequest request) {
        var response = loginService.loginByEmail(request);
        return ResponseEntity.ok(response);
    }
}
