package org.otus.platform.gateway.service.userservice.auth.registration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.otus.platform.gateway.client.UserClient;
import org.otus.platform.gateway.dto.user.auth.register.RegistrationRequest;
import org.otus.platform.gateway.dto.user.auth.register.RegistrationResponse;
import org.otus.platform.gateway.mapper.userservice.UserMapper;
import org.otus.platform.gateway.security.jwt.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final UserClient userClient;

    @Override
    public RegistrationResponse userRegister(RegistrationRequest form) {
        log.info("invoke userRegister() method");
        var encodePassword = passwordEncoder.encode(form.password());
        var createUserDto = userMapper.toCreateUserDto(form, encodePassword);
        var userDto = userClient.createUser(createUserDto).getBody();

        var accessToken = jwtService.generateAccessToken(userDto.id(), userDto.role(), userDto.name());
        var refreshToken = jwtService.generateRefreshToken();

        return new RegistrationResponse(accessToken, refreshToken);
    }
}
