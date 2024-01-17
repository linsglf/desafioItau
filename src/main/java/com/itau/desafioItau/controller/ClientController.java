package com.itau.desafioItau.controller;


import com.itau.desafioItau.entity.Client;
import com.itau.desafioItau.entity.dto.ClientDTO;
import com.itau.desafioItau.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/save")
    public ResponseEntity<Client> saveClient(@RequestBody ClientDTO client) {
        Client clientToSave = clientService.saveClient(client);
        return ResponseEntity.ok().body(clientToSave);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Client>> listAllClients() {
        return ResponseEntity.ok().body(clientService.listAllClients());
    }
}
