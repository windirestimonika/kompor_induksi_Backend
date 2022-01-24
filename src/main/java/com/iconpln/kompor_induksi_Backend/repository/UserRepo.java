package com.iconpln.kompor_induksi_Backend.repository;

import com.iconpln.kompor_induksi_Backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsernameAndIsActiveTrueAndIsDeletedFalse(String username);
}
