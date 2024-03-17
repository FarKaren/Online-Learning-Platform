package org.otus.platform.gateway.service.userservice.profile;

import org.otus.platform.gateway.dto.userservice.UserDto;
import org.otus.platform.gateway.dto.userservice.userprofile.UpdateUserRequest;
import org.otus.platform.gateway.dto.userservice.userprofile.UpdateUserResponse;
import org.otus.platform.gateway.security.user.UserRole;

import java.util.UUID;

public interface UserProfileService {
    UpdateUserResponse updateUser(UpdateUserRequest request);
    UpdateUserResponse updateUserRole(UUID id, UserRole role);
    UserDto getUserById(UUID id);
    UserDto getUserByEmail(String email);
}
