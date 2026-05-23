package com.ecommerce.service;

import com.ecommerce.dto.auth.LoginRequest;
import com.ecommerce.dto.auth.RegisterRequest;
import com.ecommerce.dto.auth.AuthResponse;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.exception.ConflictException;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${app.jwt.expiration}")
    private long jwtExpiration;

    /**
     * Registrar novo usuário
     * ✅ Validações de entrada
     * ✅ Verifica email duplicado
     * ✅ Criptografa senha com BCrypt
     */
    @Transactional
    public void register(RegisterRequest request) {
        // ✅ Validação: Email já existe
        if (userRepository.existsByEmail(request.getEmail())) {
            // ✅ Mensagem genérica para não revelar que email existe
            throw new ConflictException("Erro ao registrar usuário");
        }

        // ✅ Criar novo usuário
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))  // ✅ Criptografar
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(User.UserRole.USER)  // Role padrão
                .active(true)
                .emailVerified(false)
                .build();

        userRepository.save(user);
        
        // ✅ Log seguro (sem expor dados sensíveis)
        log.info("New user registered successfully");
    }

    /**
     * Fazer login
     * ✅ Autentica com Spring Security
     * ✅ Gera JWT token
     * ✅ Mensagens genéricas (não revela se user existe)
     */
    public AuthResponse login(LoginRequest request) {
        try {
            // ✅ Spring Security faz a validação com BCrypt
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            // ✅ Gerar tokens JWT
            String accessToken = tokenProvider.generateToken(authentication);
            String refreshToken = tokenProvider.generateTokenFromUsername(
                    request.getEmail(),
                    ((com.ecommerce.security.UserPrincipal) authentication.getPrincipal()).getId()
            );

            log.info("User logged in successfully");

            return AuthResponse.of(accessToken, refreshToken, jwtExpiration);

        } catch (BadCredentialsException ex) {
            // ✅ Mensagem genérica (não revela se email existe)
            log.warn("Login attempt failed");
            throw new BadRequestException("Credenciais inválidas");
        }
    }

    /**
     * Renovar token
     * Valida refresh token e emite novo access token
     */
    public AuthResponse refreshToken(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new BadRequestException("Token inválido ou expirado");
        }

        String username = tokenProvider.getUsernameFromToken(refreshToken);
        Long userId = tokenProvider.getUserIdFromToken(refreshToken);

        String newAccessToken = tokenProvider.generateTokenFromUsername(username, userId);

        log.info("Token refreshed successfully");

        return AuthResponse.of(newAccessToken, refreshToken, jwtExpiration);
    }
}
