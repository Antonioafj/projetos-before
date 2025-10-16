package br.com.antonio.simpletwiter.dto;

import java.util.List;

public record FeedDto(
        List<FeedItemDto> feedItens,
        int page,
        int pageSize,
        int todalPages,
        long totalElements
) {
}
