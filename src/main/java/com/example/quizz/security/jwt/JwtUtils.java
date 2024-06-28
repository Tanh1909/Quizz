package com.example.quizz.security.jwt;

import com.example.quizz.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

@Component
@Slf4j
public class JwtUtils {
    @Value("${jwt.secret}")
    private String SECRET;
    @Value("${jwt.expiration}")
    private Long EXPIRATION;
    @Value("${refresh.expiration}")
    private Long REFRESH_EXPIRATION;
    public String generateToken(User user,boolean isRefresh){
        Date now=new Date();
        Date expiration=new Date(now.getTime()+(isRefresh?REFRESH_EXPIRATION:EXPIRATION));
        Map<String,Object> claims=new HashMap<>();
        claims.put("scope",getScope(user));
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .addClaims(claims)
                .compact();
    }
    public Claims getBody(String token){
        try {
            return Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
    public String getUsername(String token){
        Claims claims=getBody(token);
        if(claims!=null){
            return claims.getSubject();
        }
        return null;
    }
    public String getScope(User user){
        StringJoiner stringJoiner=new StringJoiner(" ");
        user.getRoles().stream().forEach(role -> stringJoiner.add(role.getName()));
        return stringJoiner.toString();
    }

}
