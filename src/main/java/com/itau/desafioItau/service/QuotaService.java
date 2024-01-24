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
        if (quotaToSave.getNumberOfParticipants() == null) quotaToSave.setNumberOfParticipants(0);
        return quotaRepository.save(quotaToSave);
    }

    public Quota addClient(ArrayList<ClientDTO> clients, Long id) {
        Quota quotaToSave  = quotaRepository.findById(id).orElseThrow(() -> new NullPointerException("Quota not found"));
        if (quotaToSave.getNumberOfParticipants() == null) quotaToSave.setNumberOfParticipants(0);
        if (quotaToSave.getClients().size() + clients.size() > quotaToSave.getNumberOfParticipants()) quotaToSave.setNumberOfParticipants(quotaToSave.getClients().size() + clients.size());

        for(ClientDTO client : clients) {
            Client foundClient = clientService.findClientById(client.id());
            quotaToSave.getClients().add(foundClient);
        }
        return quotaRepository.save(quotaToSave);
    }

    public void deleteQuota(Long id) {
        quotaRepository.findById(id).orElseThrow(() -> new NullPointerException("Quota not found"));
        quotaRepository.deleteById(id);
    }
}
