package com.itau.desafioItau.controller;

import com.itau.desafioItau.entity.Client;
import com.itau.desafioItau.entity.dto.AuthenticationDTO;
import com.itau.desafioItau.entity.dto.ClientDTO;
import com.itau.desafioItau.entity.dto.LoginResponseDTO;
import com.itau.desafioItau.service.ClientService;
import com.itau.desafioItau.service.TokenService;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.cpf(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Client) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid ClientDTO data) {
        if (clientService.findByCpf(data.cpf())) throw new EntityExistsException("CPF already registered");
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        return ResponseEntity.ok().body(clientService.saveClient(data, encryptedPassword));
    }
}
