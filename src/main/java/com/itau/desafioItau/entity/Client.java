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

    @NotEmpty(message = "First name is required")
    private String firstName;

    private String lastName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    public Client(ClientDTO clientDTO) {
        this.firstName = clientDTO.firstName();
        this.lastName = clientDTO.lastName();
        this.email = clientDTO.email();
    }
}
