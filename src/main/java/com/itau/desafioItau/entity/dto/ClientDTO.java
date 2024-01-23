package com.itau.desafioItau.entity.dto;

import java.math.BigDecimal;

public record ClientDTO(
        Long id,
        String firstName,
        String lastName,
        BigDecimal participation
) {
}
