package org.otus.platform.gateway.controller.userservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.otus.platform.gateway.dto.user.userprofile.UpdateUserRequest;
import org.otus.platform.gateway.dto.user.userprofile.UpdateUserResponse;
import org.otus.platform.gateway.security.user.UserRole;
import org.otus.platform.gateway.service.userservice.profile.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
@Validated
@RequiredArgsConstructor
@Tag(
        name = "User profile controller",
        description = "User profile API"
)
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @Operation(summary = "Update user")
    ResponseEntity<UpdateUserResponse> updateUser(@Valid @RequestBody UpdateUserRequest request) {
        var response = userProfileService.updateUser(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update user role")
    ResponseEntity<UpdateUserResponse> updateUserRole(
            @NotNull @RequestParam("id") UUID id,
            @NotNull @RequestParam("role") UserRole role
    ) {
        var response = userProfileService.updateUserRole(id, role);
        return ResponseEntity.ok(response);
    }
}
