package br.com.antonio.projeto.service;

import br.com.antonio.projeto.dto.AcessDTO;
import br.com.antonio.projeto.dto.AuthenticationDTO;
import br.com.antonio.projeto.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public AcessDTO login(AuthenticationDTO authDto) {

        try {
            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authDto.getLogin(), authDto.getPassword());

            Authentication authentication = authenticationManager.authenticate(userAuth);

            UserDetailsImpl userAuthenticate = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);


            AcessDTO acessDTO = new AcessDTO(token);

            return acessDTO;

        } catch (BadCredentialsException e) {

            System.out.println("Login ou senha inválidos!");
        }
        return new AcessDTO("Acesso negado");
    }
}



















