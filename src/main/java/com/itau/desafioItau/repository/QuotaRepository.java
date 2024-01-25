package com.itau.desafioItau.repository;

import com.itau.desafioItau.entity.Quota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotaRepository extends JpaRepository<Quota, Long> {

    @Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 FROM quota_clients WHERE clients_id = ?1 AND quotas_id = ?2")
    Long isClientInQuota(Long clientId, Long quotaId);
}
