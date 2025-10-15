package br.com.antonio.simpletwiter.dto;

public record LoginRequest(
        String username,

        String password
        ) {
}
