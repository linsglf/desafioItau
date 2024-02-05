package com.itau.desafioItau.entity;

import com.itau.desafioItau.entity.dto.ClientDTO;
import jakarta.persistence.*;
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

    public Client(ClientDTO clientDTO) {
        this.firstName = clientDTO.firstName();
        this.lastName = clientDTO.lastName();
        this.email = clientDTO.email();
    }
}
