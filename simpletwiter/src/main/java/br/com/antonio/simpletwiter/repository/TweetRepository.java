package br.com.antonio.simpletwiter.repository;

import br.com.antonio.simpletwiter.entities.Role;
import br.com.antonio.simpletwiter.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
