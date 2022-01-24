package com.iconpln.kompor_induksi_Backend.domain.repository;

import com.iconpln.kompor_induksi_Backend.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByUsernameAndIsActiveTrueAndIsDeletedFalse(String username);
}
