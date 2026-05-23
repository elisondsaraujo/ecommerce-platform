package com.ecommerce.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 255, message = "Senha deve ter entre 6 e 255 caracteres")
    private String password;

    @NotBlank(message = "Primeiro nome é obrigatório")
    @Size(min = 2, max = 100, message = "Primeiro nome deve ter entre 2 e 100 caracteres")
    private String firstName;

    @NotBlank(message = "Último nome é obrigatório")
    @Size(min = 2, max = 100, message = "Último nome deve ter entre 2 e 100 caracteres")
    private String lastName;
}
