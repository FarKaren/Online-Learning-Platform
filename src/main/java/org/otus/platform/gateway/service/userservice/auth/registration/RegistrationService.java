package org.otus.platform.gateway.service.userservice.auth.registration;

import org.otus.platform.gateway.dto.userservice.auth.register.RegistrationRequest;
import org.otus.platform.gateway.dto.userservice.auth.register.RegistrationResponse;

public interface RegistrationService {
    RegistrationResponse userRegister(RegistrationRequest form);
}
