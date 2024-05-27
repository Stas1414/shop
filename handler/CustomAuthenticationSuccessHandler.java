package com.example.demo.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = "";

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_admin"))) {
            redirectUrl = "/admin";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_user"))) {
            redirectUrl = "/user/films";
        } else {
            redirectUrl = "/login?error=true";
        }

        response.sendRedirect(redirectUrl);
    }
}
