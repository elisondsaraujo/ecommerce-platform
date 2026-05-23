package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    /**
     * BCryptPasswordEncoder com strength 10 (padrão seguro)
     * - Strength 10: ~10ms de processamento (2^10 rounds)
     * - Em produção, considerar strength 12+ para maior segurança
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);  // ✅ Strength 10 é seguro
    }
}
