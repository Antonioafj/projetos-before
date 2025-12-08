package br.com.antonio.auth_api_jwt.dtos;

public record UsuarioDto(
        String nome,
        String login,
        String senha
) {
}
