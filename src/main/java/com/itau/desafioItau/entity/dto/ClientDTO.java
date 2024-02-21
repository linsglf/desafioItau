package com.itau.desafioItau.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record ClientDTO(
        Long id,
        @NotBlank(message = "Name is required")
        @Pattern(regexp = "^[^0-9]+$", message = "Name must not contain numbers")
        String firstName,
        String lastName,
        @NotEmpty(message = "Email is required")
        @Email(message = "Invalid email format")
        String email
) {
}
