package com.itau.desafioItau.service;

import com.itau.desafioItau.entity.Client;
import com.itau.desafioItau.entity.Quota;
import com.itau.desafioItau.entity.dto.ClientDTO;
import com.itau.desafioItau.entity.dto.QuotaDTO;
import com.itau.desafioItau.repository.QuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuotaService {

    @Autowired
    private QuotaRepository quotaRepository;

    @Autowired
    private ClientService clientService;

    public List<Quota> listAllQuotas() {
        return quotaRepository.findAll();
    }

    public Quota createQuota(QuotaDTO quota) {
        Quota quotaToSave = new Quota(quota.total(), quota.numberOfParticipants());
        System.out.println(quotaToSave.getNumberOfParticipants());
        return quotaRepository.save(quotaToSave);
    }

    public Quota addClient(ArrayList<ClientDTO> clients, Long id) {
        Quota quotaToSave  = quotaRepository.findById(id).orElseThrow(() -> new NullPointerException("Quota not found"));
        if (quotaToSave.getNumberOfParticipants() == null) quotaToSave.setNumberOfParticipants(0);
        if (quotaToSave.getClients().size() + clients.size() > quotaToSave.getNumberOfParticipants()) quotaToSave.setNumberOfParticipants(quotaToSave.getClients().size() + clients.size());

        List<Client> clientsToSave = quotaRepository.findById(id).get().getClients();
        for(ClientDTO client : clients) {
            clientsToSave.add(new Client(client.id()));
        }

        quotaToSave.setClients(clientsToSave);
        return quotaRepository.save(quotaToSave);
    }

    public ArrayList<ClientDTO> findClients(ArrayList<ClientDTO> clients) {
        ArrayList<Client> clientsToSave = new ArrayList<>();
        ArrayList<ClientDTO> clientsToReturn = new ArrayList<>();
        for(ClientDTO client : clients) {
            clientsToSave.add(clientService.findClientById(client.id()));
        }
        for (Client client : clientsToSave) {
            ClientDTO clientDTO = new ClientDTO(client.getId(), client.getFirstName(), client.getLastName(), client.getParticipation());
            clientsToReturn.add(clientDTO);
        }
        return clientsToReturn;
    }
}
