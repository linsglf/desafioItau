package com.itau.desafioItau.service;

import com.itau.desafioItau.entity.Client;
import com.itau.desafioItau.entity.dto.ClientDTO;
import com.itau.desafioItau.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(Client client, String encryptedPassword) {
        //if (clientRepository.findClientByCpf(client.getCpf()) != null) throw new DataIntegrityViolationException("CPF already registered");
        Client clientToSave = new Client(client, encryptedPassword);
        return clientRepository.save(clientToSave);
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NullPointerException("Client not found"));
    }

    public Client updateClient(Long id, Client client) {
        return clientRepository.findById(id)
                .map(c -> {
                    if (client.getFirstName() != null && !client.getFirstName().isEmpty()) c.setFirstName(client.getFirstName());
                    if (client.getLastName() != null && !client.getLastName().isEmpty()) c.setLastName(client.getLastName());
                    if (client.getEmail() != null && !client.getEmail().isEmpty()) c.setEmail(client.getEmail());
                    return clientRepository.save(c);
                })
                .orElseThrow(() -> new NullPointerException("Client not found"));
    }

    public void deleteClient(Long id) {
        findClientById(id);
        clientRepository.deleteById(id);
    }

    public List<Client> listAllClients() {
        List<Client> clients = clientRepository.findAll();
        if (clients == null || clients.isEmpty()) throw new NullPointerException();
        return clients;
    }

    public boolean findByCpf(String cpf) {
        return clientRepository.findClientByCpf(cpf) != null;
    }
}
