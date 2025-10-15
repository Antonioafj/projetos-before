package br.com.antonio.simpletwiter.dto;

public record CreateUserDto(
        String username,
        String password
) {
}
