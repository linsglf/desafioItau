package com.itau.desafioItau.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal participation;

    @ManyToMany(mappedBy = "clients")
    @JsonIgnore
    private List<Quota> quotas;

    public Client(Long id) {
        this.id = id;
    }
}
