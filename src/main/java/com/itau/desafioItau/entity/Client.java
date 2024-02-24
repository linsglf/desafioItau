package com.itau.desafioItau.entity;

import com.itau.desafioItau.entity.dto.ClientDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @Column(unique = true)
    private String cpf;

    public Client(ClientDTO clientDTO) {
        this.firstName = clientDTO.firstName();
        this.lastName = clientDTO.lastName();
        this.email = clientDTO.email();
        this.cpf = clientDTO.cpf();
    }
}
