package org.otus.platform.gateway.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.otus.platform.gateway.client.UserClient;
import org.otus.platform.gateway.dto.userservice.UserDto;
import org.otus.platform.gateway.security.jwt.JwtObject;
import org.otus.platform.gateway.security.jwt.JwtService;
import org.otus.platform.gateway.security.user.CustomUserDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserClient userClient;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

//        String jwtToken = Objects.requireNonNull(request.getHeader(HttpHeaders.AUTHORIZATION)).startsWith("Bearer") ?
//                request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "") : null;

        var header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String jwtToken = null;
        if (header != null && header.startsWith("Bearer")) {
            jwtToken = header.replace("Bearer ", "");
        } else {
            filterChain.doFilter(request, response);
        }

        if (jwtToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            JwtObject jwtObject = jwtService.extractData(jwtToken);

            CustomUserDetails userDetails = null;
            if (jwtObject.subject() != null) {
                UserDto userDto = userClient.getUserById(jwtObject.subject()).getBody();
                if (userDto != null) {
                    userDetails = CustomUserDetails.create(userDto);
                }
            }

            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, jwtToken, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.debug("Authorization exception: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

}
