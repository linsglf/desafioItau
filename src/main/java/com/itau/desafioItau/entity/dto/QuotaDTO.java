package com.itau.desafioItau.entity.dto;

import com.itau.desafioItau.entity.Client;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;

public record QuotaDTO(
        Long id,

        @NotNull(message = "Total is required")
        @DecimalMin(value = "0", inclusive = true, message = "Total must be zero or greater")
        BigDecimal total,

        @Min(value = 1, message = "Number of participants must be greater than zero")
        Integer numberOfParticipants,
        ArrayList<Client> clients
) {
}
