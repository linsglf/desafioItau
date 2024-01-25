package com.itau.desafioItau.entity.dto;

import java.math.BigDecimal;

public record ClientInQuotaDTO(
        Long id,
        Long clientId,
        String firstName,
        String lastName,
        BigDecimal participation
) {
}
