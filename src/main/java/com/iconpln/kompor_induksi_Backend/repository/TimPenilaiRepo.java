package com.iconpln.kompor_induksi_Backend.repository;

import com.iconpln.kompor_induksi_Backend.entity.TimPenilai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TimPenilaiRepo extends JpaRepository<TimPenilai, String>, JpaSpecificationExecutor<TimPenilai> {
}