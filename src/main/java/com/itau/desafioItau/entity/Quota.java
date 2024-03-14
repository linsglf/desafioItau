package com.itau.desafioItau.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "quota_clients",
            joinColumns = @JoinColumn(name = "quotas_id"),
            inverseJoinColumns = @JoinColumn(name = "clients_id")
    )
    private List<ClientInQuota> clientsInQuota = new ArrayList<>();


    public Quota(BigDecimal total, Integer numberOfParticipants) {
        this.total = total;
        this.numberOfParticipants= numberOfParticipants;
    }
}
