package br.com.antonio.auth_api.domain.user;

public record AuthenticationDTO(
        String login,
        String password
) {
}
