package br.com.antonio.projeto.security.jwt;

import br.com.antonio.projeto.service.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.MalformedInputException;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${projeto.jwtSecret}")
    private String jwtSecret;

    @Value("${projeto.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userData){
            return Jwts.builder()
                    .subject(userData.getUsername())
                    .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                    .signWith(getSigninKey(), SignatureAlgorithm.HS512).compact();
    }


    public SecretKey getSigninKey() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return key;
    }

    public String getUserNameToken(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(getSigninKey())
                    .build()
                    .parseSignedClaims(authToken);

            return true;

        }catch (MalformedJwtException e) {
            System.out.println("Token inválido" + e.getMessage());
        }

        return false;
    }
}












