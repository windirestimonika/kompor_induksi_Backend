package com.iconpln.kompor_induksi_Backend.service;

import com.iconpln.kompor_induksi_Backend.entity.DetailUser;
import com.iconpln.kompor_induksi_Backend.model.DetailUserDto;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

import java.time.LocalDate;
import java.util.List;

@Service
public interface DetailUserService extends BaseService<DetailUserDto, String> {
    DetailUser saveEntity(DetailUserDto dto);
    List<DetailUserDto> findByTimPenilai(@Nonnull String idUnit, @Nonnull LocalDate tanggalSelesai, @Nonnull String idIndikator);
}
