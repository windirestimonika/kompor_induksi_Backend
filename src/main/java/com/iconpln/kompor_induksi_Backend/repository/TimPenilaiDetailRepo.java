package com.iconpln.kompor_induksi_Backend.repository;

import com.iconpln.kompor_induksi_Backend.entity.TimPenilaiDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TimPenilaiDetailRepo extends JpaRepository<TimPenilaiDetail, String>, JpaSpecificationExecutor<TimPenilaiDetail> {
    @Query("select tpd from TimPenilaiDetail tpd where tpd.isDeleted = false and tpd.isActive = true and " +
            "tpd.timPenilai.periode.tanggalMulai<=:tanggal and tpd.timPenilai.periode.tanggalSelesai>=:tanggal and " +
            "tpd.indikator.id = :idIndikator and tpd.timPenilai.unit.id = :idUnit")
    List<TimPenilaiDetail> findTimPenilaiDetailByTanggalIndikatorUnit(@Param("tanggal") LocalDate tanggal, @Param("idIndikator") String idIndikator, @Param("idUnit") String idUnit);
}
