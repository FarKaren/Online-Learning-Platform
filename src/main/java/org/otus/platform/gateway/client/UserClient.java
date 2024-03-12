package org.otus.platform.gateway.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.otus.platform.gateway.dto.user.UserDto;
import org.otus.platform.gateway.dto.user.auth.register.CreateUserRequest;
import org.otus.platform.gateway.dto.user.userprofile.UpdateUserRequest;
import org.otus.platform.gateway.security.user.UserRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient("USERSERVICE/api/v1/user")
public interface UserClient {

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@NotNull @PathVariable UUID id);

    @GetMapping("/email")
    ResponseEntity<UserDto> getUserByEmail(@NotNull @RequestParam String email);

    @PostMapping
    ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserRequest dto);

    @PutMapping("/update")
    ResponseEntity<UserDto> updateUser(@Valid @RequestBody UpdateUserRequest dto);

    @PutMapping("/update/role")
    ResponseEntity<UserDto> updateUserRole(
            @NotNull @RequestParam("id") UUID id,
            @NotNull @RequestParam("role") UserRole role
    );
}

