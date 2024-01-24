package com.itau.desafioItau.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal total;
    private Integer numberOfParticipants;

    @ManyToMany
    private List<Client> clients;


    public Quota(BigDecimal total, Integer numberOfParticipants) {
        this.total = total;
        this.numberOfParticipants= numberOfParticipants;
    }

    public Quota(Quota quota) {
    }
}
