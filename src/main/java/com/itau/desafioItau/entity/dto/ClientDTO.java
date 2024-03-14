package com.itau.desafioItau.entity.dto;

import com.itau.desafioItau.entity.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record ClientDTO(
        Long id,
        @NotBlank(message = "Name is required")
        @Pattern(regexp = "^[^0-9]+$", message = "Name must not contain numbers")
        String firstName,
        String lastName,
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message = "CPF is required")
        @CPF(message = "Invalid CPF")
        String cpf,
        String password,
        UserRole role
) {
}
