package org.otus.platform.gateway.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtConfigProperties(
    String key,
    String refreshKey,
    Long expirationSec,
    Long refreshExpirationSec,
    String issuer,
    String audience
) {

}
