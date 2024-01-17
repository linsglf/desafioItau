package com.itau.desafioItau.service;

import com.itau.desafioItau.entity.Client;
import com.itau.desafioItau.entity.dto.ClientDTO;
import com.itau.desafioItau.repository.ClientRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(ClientDTO client) {
        Client clientToSave = new Client();

        clientToSave.setFirstName(client.firstName());
        clientToSave.setLastName(client.lastName());
        clientToSave.setParticipation(client.participation());
        if (clientToSave.getParticipation() == null) clientToSave.setParticipation(BigDecimal.ZERO);

        if (!isValide(clientToSave)) throw new NullPointerException("Client is not valid");

        return clientRepository.save(clientToSave);
    }

    private boolean isValide(Client client) {
        return client.getFirstName() != null
                && client.getFirstName().isEmpty() == false
                && client.getLastName() != null
                && client.getLastName().isEmpty() == false
                && client.getParticipation() != null
                && client.getParticipation().compareTo(BigDecimal.ZERO) > 0;
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NullPointerException("Client not found"));
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
}
