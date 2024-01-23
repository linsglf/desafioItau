package com.itau.desafioItau.repository;

import com.itau.desafioItau.entity.Quota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotaRepository extends JpaRepository<Quota, Long> {
}
