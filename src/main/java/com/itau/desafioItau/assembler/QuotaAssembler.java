package com.itau.desafioItau.assembler;

import com.itau.desafioItau.entity.Quota;
import com.itau.desafioItau.entity.dto.QuotaDTO;
import com.itau.desafioItau.entity.response.QuotaResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class QuotaAssembler {

    private final ModelMapper modelMapper;

    public QuotaAssembler() {
        this.modelMapper = new ModelMapper().registerModule(new RecordModule());
    }

    public QuotaResponse toResponse(Quota quota) {
        return modelMapper.map(quota, QuotaResponse.class);
    }

    public List<QuotaResponse> toResponseList(List<Quota> quotas) {
        return quotas.stream()
                .map(this::toResponse)
                .toList();
    }

    public Quota toEntity(QuotaDTO quotaDTO) {
        return modelMapper.map(quotaDTO, Quota.class);
    }
}
