package br.com.antonio.bookstore.dto;

import java.util.Set;
import java.util.UUID;

public record BookDto(
        String title,
        UUID publisherId,
        Set<UUID> authorIds,
        String reviewComment
) {
}
