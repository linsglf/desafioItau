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

    public Quota createQuota(QuotaDTO quota) {
        Quota quotaToSave = new Quota(quota.total(), quota.numberOfParticipants());
        if (quotaToSave.getNumberOfParticipants() == null) quotaToSave.setNumberOfParticipants(0);
        return quotaRepository.save(quotaToSave);
    }

    public Quota addClient(ArrayList<ClientInQuotaDTO> clients, Long id) {
        Quota quotaToSave  = quotaRepository.findById(id).orElseThrow(() -> new NullPointerException("Quota not found"));
        if (quotaToSave.getNumberOfParticipants() == null) quotaToSave.setNumberOfParticipants(0);
        if (quotaToSave.getClientsInQuota().size() + clients.size() > quotaToSave.getNumberOfParticipants()) quotaToSave.setNumberOfParticipants(quotaToSave.getClientsInQuota().size() + clients.size());

        for(ClientInQuotaDTO client : clients) {
            Client clientToSave = clientService.findClientById(client.clientId());
            if (isClientInQuota(client.clientId(), quotaToSave.getId())) throw new EntityExistsException("Client with id: " + client.clientId() + " already in quota");
            ClientInQuota clientInQuota = new ClientInQuota(client.clientId(), clientToSave.getFirstName(), clientToSave.getLastName(), client.participation());
            clientInQuota.getQuotas().add(quotaToSave);
            quotaToSave.getClientsInQuota().add(clientInQuota);
        }
        return quotaRepository.save(quotaToSave);
    }

    public Quota updateQuota(Long id, QuotaDTO quota) {
        return quotaRepository.findById(id)
                .map(q -> {
                    if (quota.total() != null) q.setTotal(quota.total());
                    if (quota.numberOfParticipants() != null) q.setNumberOfParticipants(quota.numberOfParticipants());
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
}
