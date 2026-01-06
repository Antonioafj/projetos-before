package br.com.antonio.auth_api_jwt.dtos;

import br.com.antonio.auth_api_jwt.enums.RoleEnum;

public record UsuarioDto(
        String nome,
        String login,
        String senha,
        RoleEnum role
) {
}
