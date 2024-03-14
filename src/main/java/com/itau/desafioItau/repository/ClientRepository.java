package com.itau.desafioItau.repository;

import com.itau.desafioItau.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientByCpf(String cpf);
    UserDetails findUserByCpf(String cpf);
}
