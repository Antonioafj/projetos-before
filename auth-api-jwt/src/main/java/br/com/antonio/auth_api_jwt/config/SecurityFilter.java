package br.com.antonio.auth_api_jwt.config;


import br.com.antonio.auth_api_jwt.models.Usuario;
import br.com.antonio.auth_api_jwt.repositories.UsuarioRepository;
import br.com.antonio.auth_api_jwt.services.AuthenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticacaoService authenticacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            String token = extraiTokenHeader(request);

            if (token != null) {
                String login = authenticacaoService.validatoken(token);
                Usuario usuario = usuarioRepository.findByLogin(login);

                if (usuario != null) {
                    var autentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(autentication);
                }else {
                    // Log para você saber o que está acontecendo no console
                    System.out.println("Usuário extraído do token não encontrado no banco: " + login);
                }
            }
            filterChain.doFilter(request, response);
    }

    public String extraiTokenHeader(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            return null;
        }

        if (!authHeader.split(" ")[0].equals("Bearer")) {
            return null;
        }

        return authHeader.split(" ")[1];

    }
}
