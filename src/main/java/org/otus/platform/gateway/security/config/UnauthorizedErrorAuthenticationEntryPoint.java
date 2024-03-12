package org.otus.platform.gateway.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.otus.platform.gateway.dto.error.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class UnauthorizedErrorAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Creates a response containing information about the failed authentication.
     *
     * @param request       http request.
     * @param response      http response.
     * @param authException authentication exception.
     */

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(
                objectMapper.writeValueAsString(
                        ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .header("Content-Type", "application/json")
                                .body(new ErrorDto(Integer.toString(HttpStatus.UNAUTHORIZED.value()),
                                        authException.getMessage()))));
    }
}