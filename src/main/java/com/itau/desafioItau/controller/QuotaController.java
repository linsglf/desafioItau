package com.itau.desafioItau.controller;

import com.itau.desafioItau.assembler.ClientInQuotaAssembler;
import com.itau.desafioItau.assembler.QuotaAssembler;
import com.itau.desafioItau.entity.ClientInQuota;
import com.itau.desafioItau.entity.Quota;
import com.itau.desafioItau.entity.dto.ClientInQuotaDTO;
import com.itau.desafioItau.entity.dto.QuotaDTO;
import com.itau.desafioItau.entity.response.QuotaResponse;
import com.itau.desafioItau.service.QuotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quotas")
public class QuotaController {

    @Autowired
    private QuotaService quotaService;

    @Autowired
    private QuotaAssembler quotaAssembler;

    @Autowired
    private ClientInQuotaAssembler clientInQuotaAssembler;

    @GetMapping("/list")
    public ResponseEntity<List<QuotaResponse>> listAllQuotas() {
        List<Quota> quotas = quotaService.listAllQuotas();
        return ResponseEntity.ok().body(quotaAssembler.toResponseList(quotas));
    }

    @GetMapping("/quotaPerClient/{clientId}")
    public ResponseEntity<List<QuotaResponse>> listQuotasByClient(@PathVariable Long clientId) {
        List<Quota> quotas = quotaService.listQuotasByClient(clientId);
        return ResponseEntity.ok().body(quotaAssembler.toResponseList(quotas));
    }

    @PostMapping("/create")
    public ResponseEntity<QuotaResponse> createQuota(@RequestBody @Valid QuotaDTO quota) {
        Quota quotaToSave = quotaAssembler.toEntity(quota);
        Quota savedQuota = quotaService.createQuota(quotaToSave);
        return ResponseEntity.ok().body(quotaAssembler.toResponse(savedQuota));
    }

    @PostMapping("/addClient/{id}")
    public ResponseEntity<QuotaResponse> addClient(@RequestBody ArrayList<ClientInQuotaDTO> clients, @PathVariable Long id) {
        List<ClientInQuota> clientsToAdd = clientInQuotaAssembler.toEntityList(clients);
        Quota quotaUpdated = quotaService.addClient(clientsToAdd, id);
        return ResponseEntity.ok().body(quotaAssembler.toResponse(quotaUpdated));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<QuotaResponse> updateQuota(@PathVariable Long id, @RequestBody QuotaDTO quota) {
        Quota quotaToUpdate = quotaAssembler.toEntity(quota);
        Quota updatedQuota = quotaService.updateQuota(id, quotaToUpdate);
        return ResponseEntity.ok().body(quotaAssembler.toResponse(updatedQuota));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<QuotaResponse> deleteQuota(@PathVariable Long id) {
        QuotaResponse quotaToDelete = quotaAssembler.toResponse(
                quotaService.findQuotaById(id)
        );
        quotaService.deleteQuota(id);
        return ResponseEntity.ok().body(quotaToDelete);
    }
}
