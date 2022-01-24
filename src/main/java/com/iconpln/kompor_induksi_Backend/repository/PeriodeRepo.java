package com.iconpln.kompor_induksi_Backend.repository;

import com.iconpln.kompor_induksi_Backend.entity.Periode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface PeriodeRepo extends JpaRepository<Periode, String>, JpaSpecificationExecutor<Periode> {
    List<Periode> findPeriodeByTanggalMulaiAndTanggalSelesaiAndIsActiveTrueAndIsDeletedFalse(LocalDate tanggalMulai, LocalDate tanggalSelesai);
}

