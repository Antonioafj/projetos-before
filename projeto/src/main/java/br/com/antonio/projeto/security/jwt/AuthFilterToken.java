package br.com.antonio.projeto.security.jwt;

import br.com.antonio.projeto.service.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilterToken  extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailServiceImpl service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String jwt = getToken(request);
            System.out.println( "este " + jwt);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {

                String username = jwtUtils.getUserNameToken(jwt);

                UserDetails userDetails = service.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);

            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao processar o token");
            e.printStackTrace();
        }
        filterChain.doFilter(request,response);
    }
        private String getToken (HttpServletRequest request){
            String headerToken = request.getHeader("Authorization");
            if (StringUtils.hasText(headerToken) && headerToken.startsWith("Bearer")) {
                return headerToken.replace("Bearer ", "");
            }
            return null;
        }
    }



























