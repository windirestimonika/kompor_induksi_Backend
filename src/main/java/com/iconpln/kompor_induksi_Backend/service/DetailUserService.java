package com.iconpln.kompor_induksi_Backend.service;

import com.iconpln.kompor_induksi_Backend.entity.DetailUserEntity;
import com.iconpln.kompor_induksi_Backend.model.DetailUserDto;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

public interface DetailUserService extends BaseService<DetailUserDto, String> {
    DetailUserEntity saveEntity(DetailUserDto dto);
    List<DetailUserDto> findByTimPenilai(@NotNull String idUnit, @NotNull LocalDate tanggalSelesai, @NotNull String idIndikator);
}
