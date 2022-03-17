package com.company.pastebook.config;

import com.company.pastebook.services.JwtUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailService jwtUserDetailService;
    private final JwtToken JWT_TOKEN_UTIL;

    public JwtRequestFilter(JwtToken JWT_TOKEN_UTIL){
        this.JWT_TOKEN_UTIL = JWT_TOKEN_UTIL;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String REQUEST_TOKEN_HEADER = request.getHeader("Authorization");

        String email = null;
        String jwtToken = null;


        if(REQUEST_TOKEN_HEADER != null) {
            jwtToken = REQUEST_TOKEN_HEADER;


            try {
                email = JWT_TOKEN_UTIL.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            System.out.println("JWT Token is incomplete");
        }


        if(email !=null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(email);
            if(JWT_TOKEN_UTIL.validateToken(jwtToken,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}

