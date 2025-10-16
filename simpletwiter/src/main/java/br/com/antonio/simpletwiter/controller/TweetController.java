package br.com.antonio.simpletwiter.controller;

import br.com.antonio.simpletwiter.dto.CreateTweetDto;
import br.com.antonio.simpletwiter.dto.FeedDto;
import br.com.antonio.simpletwiter.dto.FeedItemDto;
import br.com.antonio.simpletwiter.entities.Role;
import br.com.antonio.simpletwiter.entities.Tweet;
import br.com.antonio.simpletwiter.repository.TweetRepository;
import br.com.antonio.simpletwiter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TweetController {

    private final TweetRepository tweetRepository;

    private final UserRepository userRepository;

    @GetMapping("/feed")
    public ResponseEntity<FeedDto> feed(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "pageSize",defaultValue = "10") int pagesSize) {

       var tweets = tweetRepository.findAll(
               PageRequest.of(page, pagesSize, Sort.Direction.DESC, "creationTimestamp"))
               .map(tweet ->
                       new FeedItemDto(
                               tweet.getTweetId(),
                               tweet.getContent(),
                               tweet.getUser().getUsername())
               );
       return ResponseEntity.ok(new FeedDto(
               tweets.getContent(), page, pagesSize, tweets.getTotalPages(), tweets.getTotalElements()
       )) ;
    }

    @PostMapping("/tweets")
    public ResponseEntity<Void> createTweet(@RequestBody CreateTweetDto createTweetDto,
                                            JwtAuthenticationToken token) {
            var user = userRepository.findById(UUID.fromString(token.getName()));

            var tweet = new Tweet();
            tweet.setUser(user.get());
            tweet.setContent(createTweetDto.content());

            tweetRepository.save(tweet);

            return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tweets/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable("id") Long tweetId,
                                            JwtAuthenticationToken token) {

        var user = userRepository.findById(UUID.fromString(token.getName()));

        var tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var isAdmin = user.get().getRoles()
                .stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));


        if (isAdmin || tweet.getUser().getUserId().equals(UUID.fromString(token.getName()))) {
            tweetRepository.deleteById(tweetId);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok().build();
    }
}

















































