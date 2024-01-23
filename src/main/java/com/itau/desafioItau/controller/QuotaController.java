package com.itau.desafioItau.controller;

import com.itau.desafioItau.entity.Quota;
import com.itau.desafioItau.entity.dto.ClientDTO;
import com.itau.desafioItau.entity.dto.QuotaDTO;
import com.itau.desafioItau.service.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quota")
public class QuotaController {

    @Autowired
    private QuotaService quotaService;

    @GetMapping("/list")
    public ResponseEntity<List<Quota>> listAllQuotas() {
        return ResponseEntity.ok().body(quotaService.listAllQuotas());
    }

    @PostMapping("/create")
    public ResponseEntity<Quota> createQuota(@RequestBody QuotaDTO quota) {
        Quota quotaToSave = quotaService.createQuota(quota);
        return ResponseEntity.ok().body(quotaToSave);
    }

    @PostMapping("/addClient/{id}")
    public ResponseEntity<Quota> addClient(@RequestBody ArrayList<ClientDTO> clients, @PathVariable Long id) {
        ArrayList<ClientDTO> clientsToAdd = quotaService.findClients(clients);
        return ResponseEntity.ok().body(quotaService.addClient(clientsToAdd, id));
    }
}
