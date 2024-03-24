package com.itau.desafioItau.service;

import com.itau.desafioItau.entity.Client;
import com.itau.desafioItau.entity.ClientInQuota;
import com.itau.desafioItau.entity.Quota;
import com.itau.desafioItau.entity.dto.ClientInQuotaDTO;
import com.itau.desafioItau.entity.dto.QuotaDTO;
import com.itau.desafioItau.repository.QuotaRepository;
import jakarta.persistence.EntityExistsException;
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

    public Quota createQuota(Quota quota) {
        if (quota.getNumberOfParticipants() == null) quota.setNumberOfParticipants(0);
        return quotaRepository.save(quota);
    }

    public Quota addClient(List<ClientInQuota> clients, Long id) {
        Quota quotaToSave  = quotaRepository.findById(id).orElseThrow(() -> new NullPointerException("Quota not found"));
        if (quotaToSave.getNumberOfParticipants() == null) quotaToSave.setNumberOfParticipants(0);
        if (quotaToSave.getClientsInQuota().size() + clients.size() > quotaToSave.getNumberOfParticipants())
            quotaToSave.setNumberOfParticipants(quotaToSave.getClientsInQuota().size() + clients.size());

        for(ClientInQuota client : clients) {
            Client clientToSave = clientService.findClientById(client.getClientId());
            if (isClientInQuota(client.getClientId(), quotaToSave.getId())) throw new EntityExistsException("Client with id: " + client.getClientId() + " already in quota");
            ClientInQuota clientInQuota = new ClientInQuota(client.getClientId(), clientToSave.getFirstName(), clientToSave.getLastName(), client.getParticipation());
            clientInQuota.getQuotas().add(quotaToSave);
            quotaToSave.getClientsInQuota().add(clientInQuota);
        }
        return quotaRepository.save(quotaToSave);
    }

    public Quota updateQuota(Long id, Quota quota) {
        return quotaRepository.findById(id)
                .map(q -> {
                    if (quota.getTotal() != null) q.setTotal(quota.getTotal());
                    if (quota.getNumberOfParticipants() != null) q.setNumberOfParticipants(quota.getNumberOfParticipants());
                    return quotaRepository.save(q);
                })
                .orElseThrow(() -> new NullPointerException("Quota not found"));
    }

    public Boolean isClientInQuota(Long clientId, Long quotaId) {
        return quotaRepository.isClientInQuota(clientId, quotaId) > 0;
    }

    public void deleteQuota(Long id) {
        quotaRepository.findById(id).orElseThrow(() -> new NullPointerException("Quota not found"));
        quotaRepository.deleteById(id);
    }

    public Quota findQuotaById(Long id) {
        return quotaRepository.findById(id).orElseThrow(() -> new NullPointerException("Quota not found"));
    }
}
