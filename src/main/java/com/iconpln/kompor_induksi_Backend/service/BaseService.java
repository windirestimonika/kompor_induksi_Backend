package com.iconpln.kompor_induksi_Backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public interface BaseService<D, ID> {
    List<D> findAll(@Nullable String filter, @Nullable Sort sort);
    Page<D> findAll(@Nullable String filter, @Nullable Pageable pageable);
    Optional<D> save(@Nonnull D dto);
    Optional<D> getOne(@Nonnull ID id);
    Boolean delete(@Nonnull ID id);
}
