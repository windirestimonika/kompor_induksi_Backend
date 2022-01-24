package com.iconpln.kompor_induksi_Backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DatabaseConstant.TABLE_SYSTEM_USER, indexes = {@Index(name = "username_index", columnList = "username", unique = true)})
public class UserEntity extends AuditModel implements Serializable {

    private static final long serialVersionUID = -3093540612671728443L;
    @Column(name = "username", nullable = false, length = 50)
    private String username;
    private String password;
    @Column(length = 20)
    private String role;
    @OneToOne
    @JoinColumn(name = DatabaseConstant.COLUMN_DETAIL_USER)
    private DetailUser detailUser;
}
