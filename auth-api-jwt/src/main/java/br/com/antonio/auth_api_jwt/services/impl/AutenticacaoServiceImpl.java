package br.com.antonio.auth_api_jwt.services.impl;


import br.com.antonio.auth_api_jwt.dtos.AuthDto;
import br.com.antonio.auth_api_jwt.models.Usuario;
import br.com.antonio.auth_api_jwt.repositories.UsuarioRepository;
import br.com.antonio.auth_api_jwt.services.AuthenticacaoService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AutenticacaoServiceImpl implements AuthenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login);
    }


    @Override
    public String obterToken(AuthDto authDto) {

        Usuario usuario = usuarioRepository.findByLogin(authDto.login());

        return geralTokenJwt(usuario);
    }

    public String geralTokenJwt(Usuario usuario) {

        try {

            Algorithm algorithm = Algorithm.HMAC256("my-secret");

            String token = JWT.create()
                    .withIssuer("auth-api-jwt")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(geraDataExpiracao())
                    .sign(algorithm);

            return  token;
        } catch (JWTCreationException exception)  {
            throw new RuntimeException("Erro ao tentar gerar o token!" + exception.getMessage());
        }

    }

    public String validatoken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");

            return  JWT.require(algorithm)
                    .withIssuer("auth-api-jwt")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant geraDataExpiracao() {

        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));

    }
}
