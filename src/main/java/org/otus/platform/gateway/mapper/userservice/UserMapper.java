package org.otus.platform.gateway.mapper.userservice;

import org.otus.platform.gateway.dto.userservice.UserDto;
import org.otus.platform.gateway.dto.userservice.auth.register.CreateUserRequest;
import org.otus.platform.gateway.dto.userservice.auth.register.RegistrationRequest;
import org.otus.platform.gateway.dto.userservice.userprofile.UpdateUserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public CreateUserRequest toCreateUserDto(RegistrationRequest form, String encodePassword) {
        return CreateUserRequest.builder()
                .name(form.name())
                .email(form.email())
                .phone(form.phone())
                .password(encodePassword)
                .build();
    }
    public UpdateUserResponse toUpdateUserResponse(UserDto dto) {
        return UpdateUserResponse.builder()
                .id(dto.id())
                .email(dto.email())
                .name(dto.name())
                .phone(dto.phone())
                .role(dto.role())
                .build();
    }
}
