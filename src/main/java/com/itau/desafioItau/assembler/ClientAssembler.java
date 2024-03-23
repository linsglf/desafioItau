package com.itau.desafioItau.assembler;

import com.itau.desafioItau.entity.Client;
import com.itau.desafioItau.entity.dto.ClientDTO;
import com.itau.desafioItau.entity.response.ClientResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.record.RecordModule;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ClientAssembler {

    private final ModelMapper modelMapper;

    public ClientAssembler() {
       this.modelMapper= new ModelMapper().registerModule(new RecordModule());
    }

    public ClientResponse toResponse(Client client) {
        return modelMapper.map(client, ClientResponse.class);
    }

    public List<ClientResponse> toResponseList(List<Client> clients) {
        return clients.stream()
                .map(this::toResponse)
                .toList();
    }

    public Client toEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }
}
