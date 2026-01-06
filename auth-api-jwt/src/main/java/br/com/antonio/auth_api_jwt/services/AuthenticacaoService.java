package br.com.antonio.auth_api_jwt.services;

import br.com.antonio.auth_api_jwt.dtos.AuthDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticacaoService extends UserDetailsService {



    public String obterToken(AuthDto authDto);

}
