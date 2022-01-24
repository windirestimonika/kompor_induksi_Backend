package com.iconpln.kompor_induksi_Backend.assembler;

import org.jetbrains.annotations.NotNull;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseAssembler<E,D> {
    E toEntity(@NotNull D dto);
    D toDto(@NotNull E entity);
    E fromDto(@NotNull D dto, @MappingTarget E entity);
    List<E> toEntities(@NotNull List<D> dtos);
    List<D> toDtos(@NotNull List<E> entities);
}
