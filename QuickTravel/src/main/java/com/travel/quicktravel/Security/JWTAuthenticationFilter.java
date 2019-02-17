package com.travel.quicktravel.Security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationmanager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationmanager) {
        super();
        this.authenticationmanager = authenticationmanager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        com.travel.quicktravel.entities.Account account = null;
        try {
            account = new ObjectMapper().readValue(request.getInputStream(), com.travel.quicktravel.entities.Account.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        User springuser = (User) authResult.getPrincipal();
        String jwt = Jwts.builder().setSubject(springuser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SecurityConstant.SECRET)
                .claim("roles", springuser.getAuthorities())
                .compact();
        response.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX + jwt);
    }
}
