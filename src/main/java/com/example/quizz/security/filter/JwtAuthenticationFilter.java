package com.example.quizz.security.filter;

import com.example.quizz.dto.response.UserResponse;
import com.example.quizz.security.jwt.JwtUtils;
import com.example.quizz.security.service.UserDetailImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=getTokenFromRequest(request);
        if(token!=null){
            Claims claims=jwtUtils.getBody(token);
            if(claims!=null){
                UserDetailImpl userDetail=new UserDetailImpl();
                userDetail.setUsername(claims.getSubject());
                String[] scopes=((String)claims.get("scope")).trim().split(" ");
                List<GrantedAuthority> authorities= Arrays.stream(scopes).map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList());
                Authentication authentication=new UsernamePasswordAuthenticationToken(userDetail,null,authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(request,response);
    }
    private String getTokenFromRequest(HttpServletRequest request){
        String authToken=request.getHeader("Authorization");
        if(authToken!=null&&authToken.startsWith("Bearer ")){
            return  authToken.substring(7);
        }
        return null;
    }
}
