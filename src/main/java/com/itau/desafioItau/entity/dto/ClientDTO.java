package com.itau.desafioItau.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record ClientDTO(
        Long id,
        @NotBlank(message = "Name is required")
        String firstName,
        String lastName,
        @NotEmpty(message = "Email is required")
        @Email(message = "Invalid email format")
        String email
) {
}
