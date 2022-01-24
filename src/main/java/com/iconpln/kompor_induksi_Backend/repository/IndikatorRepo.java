package com.iconpln.kompor_induksi_Backend.repository;

import com.iconpln.kompor_induksi_Backend.entity.Indikator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IndikatorRepo extends JpaRepository<Indikator, String>, JpaSpecificationExecutor<Indikator> {
}

