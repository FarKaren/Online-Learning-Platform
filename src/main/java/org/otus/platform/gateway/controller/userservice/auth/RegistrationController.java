package org.otus.platform.gateway.controller.userservice.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.otus.platform.gateway.dto.user.auth.register.RegistrationRequest;
import org.otus.platform.gateway.dto.user.auth.register.RegistrationResponse;
import org.otus.platform.gateway.service.userservice.auth.registration.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/register")
@Validated
@RequiredArgsConstructor
@Tag(
        name = "Registration controller",
        description = "Registration API"
)
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    @Operation(summary = "Registration")
    ResponseEntity<RegistrationResponse> userRegister(@Valid @RequestBody RegistrationRequest form) {
        var userDto = registrationService.userRegister(form);
        return ResponseEntity.ok(userDto);
    }
}
