package com.iconpln.kompor_induksi_Backend.repository;

import com.iconpln.kompor_induksi_Backend.entity.DetailUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DetailUserRepo extends JpaRepository<DetailUserEntity, String>, JpaSpecificationExecutor<DetailUserEntity> {
}
