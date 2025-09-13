package br.com.antonio.helpdesk.security;


import br.com.antonio.helpdesk.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            if (!hasAuthorizationBearer(request)) {
                filterChain.doFilter(request, response);
                return;
        }
            String token = getAccessToken(request);


        if (!jwtUtils.isTokenValid(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            setAuthenticationContext(token, request);
            filterChain.doFilter(request, response);
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        return header != null && !header.isEmpty() && header.startsWith("Bearer");

    }

    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return  header.split(" ")[1].trim();
    }

    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private  UserDetails getUserDetails(String token) {
        String jwtSubject = jwtUtils.extractSubject(token);
        if (jwtSubject != null) {
            CustomUserDetails userDetails = new CustomUserDetails(userService.findByUsername(jwtSubject));
            if (userDetails != null) {
                return userDetails;
            }
        }
        throw new RuntimeException("jwtSubject is null");
    }
}
