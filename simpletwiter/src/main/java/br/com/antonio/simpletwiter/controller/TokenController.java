package br.com.antonio.simpletwiter.controller;


import br.com.antonio.simpletwiter.dto.LoginRequest;
import br.com.antonio.simpletwiter.dto.LoginResponse;
import br.com.antonio.simpletwiter.entities.Role;
import br.com.antonio.simpletwiter.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class TokenController {

    private final JwtEncoder encoder;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtEncoder encoder,
                           UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

       var user = userRepository.findByUsername(loginRequest.username());

       if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)) {
           throw new BadCredentialsException("user or Password is invalid!");
       }

       var now = Instant.now();
       var expiresIn = 300L;

       var scopes = user.get().getRoles()
               .stream()
               .map(Role::getName)
               .collect(Collectors.joining(" "));

       var claims = JwtClaimsSet.builder()
               .issuer("simpletwiter")
               .subject(user.get().getUserId().toString())
               .issuedAt(now)
               .issuedAt(now.plusSeconds(expiresIn))
               .claim("scope", scopes)
               .build();

       var jwtValue = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

       return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }
}

































