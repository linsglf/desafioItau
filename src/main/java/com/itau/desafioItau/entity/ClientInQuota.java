package com.itau.desafioItau.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor
@AllArgsConstructor
public class ClientInQuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private BigDecimal participation;

    @ManyToMany(mappedBy = "clientsInQuota")
    @JsonIgnore
    private List<Quota> quotas = new ArrayList<>();

    public ClientInQuota(Long clientId, String clientFirstName, String clientLastName, BigDecimal participation) {
        this.clientId = clientId;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.participation = participation;
    }
}
