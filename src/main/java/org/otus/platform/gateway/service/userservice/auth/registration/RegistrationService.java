package org.otus.platform.gateway.service.userservice.auth.registration;

import org.otus.platform.gateway.dto.user.auth.register.RegistrationRequest;
import org.otus.platform.gateway.dto.user.auth.register.RegistrationResponse;

public interface RegistrationService {
    RegistrationResponse userRegister(RegistrationRequest form);
}
