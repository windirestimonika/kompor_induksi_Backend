package com.iconpln.kompor_induksi_Backend.repository;

import com.iconpln.kompor_induksi_Backend.entity.SuratKeputusan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SuratKeputusanRepo extends JpaRepository<SuratKeputusan, String>, JpaSpecificationExecutor<SuratKeputusan> {
}