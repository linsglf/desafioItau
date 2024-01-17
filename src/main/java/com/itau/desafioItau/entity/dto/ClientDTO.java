package com.itau.desafioItau.entity.dto;

import java.math.BigDecimal;

public record ClientDTO(
        String firstName,
        String lastName,
        BigDecimal participation
) {
}
