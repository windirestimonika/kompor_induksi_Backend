package com.iconpln.kompor_induksi_Backend.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface BaseService<D, ID> {
    List<D> findAll(@Nullable String filter, @Nullable Sort sort);
    Page<D> findAll(@Nullable String filter, @Nullable Pageable pageable);
    Optional<D> save(@NotNull D dto);
    Optional<D> getOne(@NotNull ID id);
    Boolean delete(@NotNull ID id);
}
