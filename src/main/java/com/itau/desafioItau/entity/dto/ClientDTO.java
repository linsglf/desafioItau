package com.itau.desafioItau.entity.dto;

public record ClientDTO(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
