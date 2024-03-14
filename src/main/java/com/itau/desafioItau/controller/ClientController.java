package com.itau.desafioItau.controller;


import com.itau.desafioItau.entity.Client;
import com.itau.desafioItau.entity.dto.ClientDTO;
import com.itau.desafioItau.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/save")
    public ResponseEntity<Client> saveClient(@RequestBody @Valid ClientDTO client) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(client.password());
        return ResponseEntity.ok().body(clientService.saveClient(client, encryptedPassword));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Client>> listAllClients() {
        return ResponseEntity.ok().body(clientService.listAllClients());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable Long id) {
        return ResponseEntity.ok().body(clientService.findClientById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody ClientDTO client) {
        Client clientToUpdate = clientService.updateClient(id, client);
        return ResponseEntity.ok().body(clientToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
