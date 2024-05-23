package com.immobylette.api.photo.security;

import com.immobylette.api.photo.config.AuthConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
@Order(1)
public class AuthenticationFilter extends OncePerRequestFilter {
    private final AuthConfig authConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader("X-Api-Key");
        if (apiKey == null || apiKey.isEmpty() || !apiKey.equals(authConfig.getApiKey())){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
            return;
        }

        filterChain.doFilter(request, response);
    }
}