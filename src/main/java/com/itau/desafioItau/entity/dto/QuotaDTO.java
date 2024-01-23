package com.itau.desafioItau.entity.dto;

import com.itau.desafioItau.entity.Client;

import java.math.BigDecimal;
import java.util.ArrayList;

public record QuotaDTO(
        Long id,
        BigDecimal total,
        Integer numberOfParticipants,
        ArrayList<Client> clients
) {
}
