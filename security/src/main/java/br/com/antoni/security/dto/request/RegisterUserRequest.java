package br.com.antoni.security.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequest(

        @NotEmpty(message = "Nome é obrigatório")
        String nome,

        @NotEmpty(message = "Email é obrigatório")
        String email,

        @NotEmpty(message = "Senha é obrigatória")
        String password
) {
}
