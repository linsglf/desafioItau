package com.itau.desafioItau.entity.response;

import com.itau.desafioItau.entity.ClientInQuota;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuotaResponse {

    private String total;
    private Integer numberOfParticipants;
    private List<ClientInQuota> clientsInQuota;

}
