package br.com.antonio.simpletwiter.dto;

public record FeedItemDto(

        long tweetId,

        String content,

        String username
) {
}
