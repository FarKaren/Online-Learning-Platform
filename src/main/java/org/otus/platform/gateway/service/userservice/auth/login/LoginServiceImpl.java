package org.otus.platform.gateway.service.userservice.auth.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.otus.platform.gateway.client.UserClient;
import org.otus.platform.gateway.dto.userservice.auth.login.LoginRequest;
import org.otus.platform.gateway.dto.userservice.auth.login.LoginResponse;
import org.otus.platform.gateway.exception.exceptions.AuthenticationFailedException;
import org.otus.platform.gateway.exception.exceptions.IncorrectPasswordException;
import org.otus.platform.gateway.security.jwt.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse loginByEmail(LoginRequest request) {
        log.info("invoke loginByToken() method");
        var userDto = userClient.getUserByEmail(request.email()).getBody();
        if (userDto == null) {
            throw new AuthenticationFailedException();
        }
        var passwordMatches = passwordEncoder.matches(request.password(), userDto.password());
        if (!passwordMatches) {
            throw new IncorrectPasswordException("Incorrect password");
        }

        var accessToken = jwtService.generateAccessToken(userDto.id(), userDto.role(), userDto.name());
        var refreshToken = jwtService.generateRefreshToken();

        return new LoginResponse(accessToken, refreshToken);
    }
}
