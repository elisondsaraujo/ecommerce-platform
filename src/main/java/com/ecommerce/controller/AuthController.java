package com.ecommerce.controller;

import com.ecommerce.dto.auth.LoginRequest;
import com.ecommerce.dto.auth.RegisterRequest;
import com.ecommerce.dto.auth.AuthResponse;
import com.ecommerce.dto.response.ApiResponse;
import com.ecommerce.service.AuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "${app.security.cors.allowed-origins:http://localhost:3000}")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * POST /auth/register
     * Registrar novo usuário
     * ✅ Valida entrada com @Valid
     * ✅ Não retorna detalhes de erro
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody RegisterRequest request) {
        try {
            authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success(null, "Usuário registrado com sucesso"));
        } catch (Exception ex) {
            // ✅ Mensagem genérica para não revelar detalhes
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Erro ao registrar usuário"));
        }
    }

    /**
     * POST /auth/login
     * Fazer login e obter JWT token
     * ✅ Valida entrada com @Valid
     * ✅ Retorna accessToken e refreshToken
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(
                ApiResponse.success(response, "Login realizado com sucesso")
        );
    }

    /**
     * POST /auth/refresh
     * Renovar access token com refresh token
     */
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AuthResponse>> refresh(
            @RequestHeader("X-Refresh-Token") String refreshToken) {
        AuthResponse response = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(
                ApiResponse.success(response, "Token renovado com sucesso")
        );
    }

    /**
     * POST /auth/logout
     * Fazer logout (invalidar token)
     * Nota: Em APIs stateless, o logout é apenas client-side
     * Para invalidar no servidor, usar blacklist/cache
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout() {
        // ✅ Em aplicação stateless, logout é feito removendo token no client
        return ResponseEntity.ok(
                ApiResponse.success(null, "Logout realizado com sucesso")
        );
    }
}
