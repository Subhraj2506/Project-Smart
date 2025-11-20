package com.egov.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {
    private final Key key; private final long expirationMs = 1000*60*60*6;
    public JwtUtil(@Value("${app.jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    public String generateToken(String subject){
        Date now=new Date(); 
        Date exp=new Date(now.getTime()+expirationMs);
        return Jwts.builder().setSubject(subject).setIssuedAt(now).setExpiration(exp).signWith(key).compact(); 
    }
    public boolean validateToken(String token){
        try { 
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException|IllegalArgumentException ex){
            return false; 
        }
    }
    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }
}
