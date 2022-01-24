package com.iconpln.kompor_induksi_Backend.assembler;

import com.iconpln.kompor_induksi_Backend.entity.User;
import com.iconpln.kompor_induksi_Backend.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        uses = DetailUserAssembler.class)
public interface UserAssembler extends BaseAssembler<User, UserDto> {
    @Mapping(target = "password", ignore = true)
    UserDto toDto(User user);
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "detailUser", ignore = true)
    User toEntity(UserDto dto);
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "detailUser", ignore = true)
    User fromDto(UserDto dto, @MappingTarget User user);
}