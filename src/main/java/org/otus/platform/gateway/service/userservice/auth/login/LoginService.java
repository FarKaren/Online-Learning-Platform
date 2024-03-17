package org.otus.platform.gateway.service.userservice.auth.login;

import org.otus.platform.gateway.dto.userservice.auth.login.LoginRequest;
import org.otus.platform.gateway.dto.userservice.auth.login.LoginResponse;

public interface LoginService {
    LoginResponse loginByEmail(LoginRequest request);
}
