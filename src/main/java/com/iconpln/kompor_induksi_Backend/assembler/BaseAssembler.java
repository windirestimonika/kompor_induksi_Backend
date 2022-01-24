package com.iconpln.kompor_induksi_Backend.assembler;

import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;

@Component
public interface BaseAssembler<E,D> {
    E toEntity(@Nonnull D dto);
    D toDto(@Nonnull E entity);
    E fromDto(@Nonnull D dto, @MappingTarget E entity);
    List<E> toEntities(@Nonnull List<D> dtos);
    List<D> toDtos(@Nonnull List<E> entities);
}
