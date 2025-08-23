package br.com.antonio.helpdesk.dto;

public record CreatedUserDto(
        String username,
        String password,
        String name,
        String email
) {
}
