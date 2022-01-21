package com.iconpln.kompor_induksi_Backend.entity;

import com.iconpln.kompor_induksi_Backend.constant.Database;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Database.TABLE_SYSTEM_USER, indexes = {@Index(name = "username_index", columnList = "username", unique = true)})
public class User extends AuditModel implements Serializable {

    @Column(name = "username", nullable = false, length = 50)
    private String username;
    private String password;

    @Column(length = 20)
    private String role;

    @OneToOne
    @JoinColumn(name = Database.COLUMN_DETAIL_USER)
    private DetailUser detailUser;
}