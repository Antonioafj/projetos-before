package br.com.antoni.security.config;

import lombok.Builder;

@Builder
public record JWTUserDate(Long userId, String email) {
}
