package br.com.antonio.login.controllers;

import br.com.antonio.login.domain.repository.UserRepository;
import br.com.antonio.login.domain.user.User;
import br.com.antonio.login.dto.LoginRequestDTO;
import br.com.antonio.login.dto.RegisterRequestDTO;
import br.com.antonio.login.dto.ResponseDTO;
import br.com.antonio.login.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
            User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User Not Found"));
            if(passwordEncoder.matches(body.password(), user.getPassword())){
                String token = this.tokenService.generateToken(user);
                return  ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
            return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
       Optional<User> user = repository.findByEmail(body.email());

       if (user.isEmpty()){
           User newUser = new User();
           newUser.setName(body.name());
           newUser.setEmail(body.email());
           newUser.setPassword(passwordEncoder.encode(body.password()));
           this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return  ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}





























