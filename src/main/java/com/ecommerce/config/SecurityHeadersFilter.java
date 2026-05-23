package com.ecommerce.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro para adicionar headers de segurança HTTP
 * Previne:
 * - Clickjacking (X-Frame-Options)
 * - MIME Sniffing (X-Content-Type-Options)
 * - XSS (X-XSS-Protection)
 * - Referrer Leaking (Referrer-Policy)
 */
@Slf4j
@Configuration
public class SecurityHeadersFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // ✅ Previne clickjacking
        response.setHeader("X-Frame-Options", "DENY");

        // ✅ Previne MIME sniffing
        response.setHeader("X-Content-Type-Options", "nosniff");

        // ✅ Ativa proteção XSS no navegador
        response.setHeader("X-XSS-Protection", "1; mode=block");

        // ✅ Política de referrer (não enviar origem em navegação)
        response.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");

        // ✅ Content Security Policy (CSP) - restritivo
        response.setHeader("Content-Security-Policy", "default-src 'self'");

        // ✅ HSTS - Força HTTPS por 1 ano
        response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");

        // ✅ Permissões da Feature Policy
        response.setHeader("Permissions-Policy", 
            "geolocation=(), microphone=(), camera=()");

        filterChain.doFilter(request, response);
    }
}
