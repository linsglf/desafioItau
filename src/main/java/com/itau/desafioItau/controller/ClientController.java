package com.itau.desafioItau.controller;


import com.itau.desafioItau.assembler.ClientAssembler;
import com.itau.desafioItau.entity.Client;
import com.itau.desafioItau.entity.dto.ClientDTO;
import com.itau.desafioItau.entity.response.ClientResponse;
import com.itau.desafioItau.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientAssembler clientAssembler;

    @PostMapping("/save")
    public ResponseEntity<ClientResponse> saveClient(@RequestBody @Valid ClientDTO client) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(client.password());
        Client clientToSave = clientAssembler.toEntity(client);
        Client savedClient = clientService.saveClient(clientToSave, encryptedPassword);
        return ResponseEntity.ok().body(clientAssembler.toResponse(savedClient));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClientResponse>> listAllClients() {
        List<Client> clients = clientService.listAllClients();
        return ResponseEntity.ok().body(clientAssembler.toResponseList(clients));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ClientResponse> findClientById(@PathVariable Long id) {
        Client client = clientService.findClientById(id);
        return ResponseEntity.ok().body(clientAssembler.toResponse(client));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable Long id, @RequestBody @Valid ClientDTO client) {
        Client clientToUpdate = clientAssembler.toEntity(client);
        Client updatedClient = clientService.updateClient(id, clientToUpdate);
        return ResponseEntity.ok().body(clientAssembler.toResponse(updatedClient));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
