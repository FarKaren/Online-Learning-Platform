package org.otus.platform.gateway.service.userservice.profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.otus.platform.gateway.client.UserClient;
import org.otus.platform.gateway.dto.userservice.UserDto;
import org.otus.platform.gateway.dto.userservice.userprofile.UpdateUserRequest;
import org.otus.platform.gateway.dto.userservice.userprofile.UpdateUserResponse;
import org.otus.platform.gateway.mapper.userservice.UserMapper;
import org.otus.platform.gateway.security.user.UserRole;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserClient userClient;
    private final UserMapper userMapper;

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest request) {
        log.info("invoke updateUser() method");
        var userDto = userClient.updateUser(request).getBody();
        return userMapper.toUpdateUserResponse(userDto);
    }

    @Override
    public UpdateUserResponse updateUserRole(UUID id, UserRole role) {
        log.info("invoke updateUserRole() method");
        var userDto = userClient.updateUserRole(id, role).getBody();
        return userMapper.toUpdateUserResponse(userDto);
    }

    @Override
    public UserDto getUserById(UUID id) {
        log.info("invoke getUserById() method");
        return userClient.getUserById(id).getBody();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        log.info("invoke getUserByEmail() method");
        return userClient.getUserByEmail(email).getBody();
    }
}
