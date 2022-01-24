package com.iconpln.kompor_induksi_Backend.repository;

import com.iconpln.kompor_induksi_Backend.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UnitRepo extends JpaRepository<Unit, String>, JpaSpecificationExecutor<Unit> {
}
