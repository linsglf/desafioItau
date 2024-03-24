package com.itau.desafioItau.assembler;

import com.itau.desafioItau.entity.ClientInQuota;
import com.itau.desafioItau.entity.dto.ClientInQuotaDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ClientInQuotaAssembler {

    private final ModelMapper modelMapper;

    public ClientInQuotaAssembler() {
        this.modelMapper = new ModelMapper().registerModule(new RecordModule());
    }

    public List<ClientInQuota> toEntityList(ArrayList<ClientInQuotaDTO> clients) {
        return clients.stream()
                .map(this::toEntity)
                .toList();
    }

    public ClientInQuota toEntity(ClientInQuotaDTO client) {
        return modelMapper.map(client, ClientInQuota.class);
    }
}
