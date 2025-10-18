package br.com.antoni.security.controller;

import br.com.antoni.security.dto.request.LoginRequest;
import br.com.antoni.security.dto.request.RegisterUserRequest;
import br.com.antoni.security.dto.response.LoginResponse;
import br.com.antoni.security.dto.response.RegisterUserResponse;
import br.com.antoni.security.entity.User;
import br.com.antoni.security.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final UserRepository userRepository;

    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return null;
    }

    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        User newUser = new User();
        newUser.setName(request.nome());
        newUser.setEmail(request.email());
        newUser.setPassword(request.password());

        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(newUser.getName(),
                newUser.getEmail()));
    }
}



























