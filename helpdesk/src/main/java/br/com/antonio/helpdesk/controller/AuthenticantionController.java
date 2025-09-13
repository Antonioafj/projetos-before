package br.com.antonio.helpdesk.controller;

import br.com.antonio.helpdesk.domain.User;
import br.com.antonio.helpdesk.dto.AuthResponseDto;
import br.com.antonio.helpdesk.dto.CreatedUserDto;
import br.com.antonio.helpdesk.dto.LoginRequestDto;
import br.com.antonio.helpdesk.dto.UserDTO;
import br.com.antonio.helpdesk.mapper.UserMapper;
import br.com.antonio.helpdesk.security.CustomUserDetails;
import br.com.antonio.helpdesk.security.JwtUtils;
import br.com.antonio.helpdesk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthenticantionController {

    private final UserService userService;

    private final UserMapper mapper;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager autheManager;


    @PostMapping(value = "token")
    public ResponseEntity doLogin(@RequestBody LoginRequestDto request) {
        try {
            Authentication authentication = autheManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            AuthResponseDto tokenResponse = jwtUtils.generateToken(userDetails.getUsername());

            return ResponseEntity.ok().body(tokenResponse);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

























