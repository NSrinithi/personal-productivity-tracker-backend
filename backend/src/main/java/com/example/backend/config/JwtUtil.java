package com.example.backend.config;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private final Key key = Keys.hmacShaKeyFor("THIS_IS_A_VERY_LONG_SECRET_KEY_12345678901234567890".getBytes());

    // private static final String secretKey="this-is-a-very-long-secret-key-for-hs256-algorithm";

    private final long expiration_time=60*60*1000;//1 hour

    public String generateToken(String email){
        return  Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+expiration_time)).setSubject(email).signWith(key).compact();
    }


    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }


    public String extractEmail(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }


}
