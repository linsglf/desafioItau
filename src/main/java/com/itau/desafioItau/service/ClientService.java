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

    public Client saveClient(ClientDTO clientDTO, String encryptedPassword) {
        if (clientRepository.findClientByCpf(clientDTO.cpf()) != null) throw new DataIntegrityViolationException("CPF already registered");
        Client clientToSave = new Client(clientDTO, encryptedPassword);
        return clientRepository.save(clientToSave);
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NullPointerException("Client not found"));
    }

    public Client updateClient(Long id, ClientDTO clientDTO) {
        return clientRepository.findById(id)
                .map(c -> {
                    if (clientDTO.firstName() != null && !clientDTO.firstName().isEmpty()) c.setFirstName(clientDTO.firstName());
                    if (clientDTO.lastName() != null && !clientDTO.lastName().isEmpty()) c.setLastName(clientDTO.lastName());
                    if (clientDTO.email() != null && !clientDTO.email().isEmpty()) c.setEmail(clientDTO.email());
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
