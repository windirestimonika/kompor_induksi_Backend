package com.iconpln.kompor_induksi_Backend.entity;

import com.iconpln.kompor_induksi_Backend.constant.Database;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Database.TABLE_MASTER_UNIT)
public class Unit extends AuditModel implements Serializable {

    @Column(length = 10)
    private String kode;

    @Column(length = 100)
    private String nama;

    @Column(length = 15)
    private String level;
    private Integer levelInt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Database.COLUMN_PARENT_UNIT)
    private Unit parentUnit;
}
