package br.com.antonio.simpletwiter.dto;

public record LoginResponse(
        String accessToken,
        Long expiresIn
) {
}
