package com.iconpln.kompor_induksi_Backend.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AuditModel implements Serializable {


    @Id
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;

    @CreatedBy
    @Column(name = "user_created", updatable = false)
    private String userCreated;

    @CreatedDate
    @Column(name = "timecreated", updatable = false)
    private LocalDateTime timeCreated;

    @LastModifiedBy
    @Column(name = "user_update")
    private String userUpdate;

    @LastModifiedDate
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    private Boolean isActive;
    private Boolean isDeleted;

    @PrePersist
    public void prePersist() {
        id = UUID.randomUUID().toString();
        lastUpdate = timeCreated = LocalDateTime.now();
        isActive = isActive == null ? true : isActive;
        isDeleted = isDeleted == null ? false : isDeleted;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}
