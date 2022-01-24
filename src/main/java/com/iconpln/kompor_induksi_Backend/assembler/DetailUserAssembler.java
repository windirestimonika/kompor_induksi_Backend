package com.iconpln.kompor_induksi_Backend.assembler;

import com.iconpln.kompor_induksi_Backend.entity.DetailUser;
import com.iconpln.kompor_induksi_Backend.model.DetailUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface DetailUserAssembler extends BaseAssembler<DetailUser, DetailUserDto> {
    @Mapping(target = "idUnit", source = "unit.id")
    @Mapping(target = "kodeUnit", source = "unit.kode")
    @Mapping(target = "namaUnit", source = "unit.nama")
    @Mapping(target = "idUnit1", source = "unit1.id")
    @Mapping(target = "kodeUnit1", source = "unit1.kode")
    @Mapping(target = "namaUnit1", source = "unit1.nama")
    @Mapping(target = "idUnit2", source = "unit2.id")
    @Mapping(target = "kodeUnit2", source = "unit2.kode")
    @Mapping(target = "namaUnit2", source = "unit2.nama")
    @Mapping(target = "idUnit3", source = "unit3.id")
    @Mapping(target = "kodeUnit3", source = "unit3.kode")
    @Mapping(target = "namaUnit3", source = "unit3.nama")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "role", source = "user.role")
    DetailUserDto toDto(DetailUser detailUser);
}

