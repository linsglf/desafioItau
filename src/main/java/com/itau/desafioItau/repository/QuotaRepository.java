package com.itau.desafioItau.repository;

import com.itau.desafioItau.entity.Quota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotaRepository extends JpaRepository<Quota, Long> {

    @Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 FROM quota_clients WHERE clients_id = ?1 AND quotas_id = ?2")
    Long isClientInQuota(Long clientId, Long quotaId);

    @Query(nativeQuery = true, value = "SELECT ciq.id FROM client_in_quota ciq WHERE ciq.client_id = ?1")
    List<Long> getClientInQuotaIds(Long clientId);

    @Query(nativeQuery = true, value = "SELECT qc.quotas_id FROM quota_clients qc WHERE qc.clients_id = ?1")
    List<Long> getQuotaIdsByClientInQuotaId(Long clientInQuotaId);
}
