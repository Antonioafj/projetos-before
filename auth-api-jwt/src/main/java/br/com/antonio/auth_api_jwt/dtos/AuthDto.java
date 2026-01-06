package br.com.antonio.auth_api_jwt.dtos;

public record AuthDto(
        String login,
        String senha
) {
}
