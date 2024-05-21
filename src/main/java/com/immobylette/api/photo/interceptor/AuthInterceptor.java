package com.immobylette.api.photo.interceptor;

import com.immobylette.api.photo.config.AuthConfig;
import com.immobylette.api.photo.exception.AuthMissingException;
import com.immobylette.api.photo.exception.AuthWrongException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final AuthConfig authConfig;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String header = request.getHeader("X-Api-Key");
        if (header == null || header.isEmpty()) {
            throw new AuthMissingException();
        }
        if (!header.equals(authConfig.getApiKey())) {
            throw new AuthWrongException();
        }

        return true;
    }
}
