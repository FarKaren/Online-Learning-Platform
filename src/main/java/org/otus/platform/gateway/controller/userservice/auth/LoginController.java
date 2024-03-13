package org.otus.platform.gateway.controller.userservice.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Login controller",
        description = "Login API"
)
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    //@Operation(summary = "Login by email")
    @Operation(summary = "Get All Employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Employee List",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginRequest.class)) }),
            @ApiResponse(responseCode = "204",
                    description = "No employee found",
                    content = @Content) })
    ResponseEntity<LoginResponse> loginByEmail(@Valid @RequestBody LoginRequest request) {
        var response = loginService.loginByEmail(request);
        return ResponseEntity.ok(response);
    }
}
