package com.iconpln.kompor_induksi_Backend.domain;

import com.iconpln.kompor_induksi_Backend.constant.Database;
import com.iconpln.kompor_induksi_Backend.entity.Indikator;
import com.iconpln.kompor_induksi_Backend.entity.Unit;
import com.iconpln.kompor_induksi_Backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Database.TABLE_MASTER_DETAIL_USER)
public class DetailUser extends AuditModel implements Serializable {
    private static final long serialVersionUID = -7887186428752241919L;
    @Column(length = 30)
    private String nama;
    @Column(length = 15)
    private String nip;
    @Column(length = 100)
    private String jabatan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Database.COLUMN_UNIT)
    private Unit unit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Database.COLUMN_UNIT_1)
    private Unit unit1;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Database.COLUMN_UNIT_2)
    private Unit unit2;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Database.COLUMN_UNIT_3)
    private Unit unit3;
    @Column(length = 50)
    private String email;
    @OneToOne(mappedBy = "detailUser")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Database.COLUMN_INDIKATOR)
    private Indikator indikator;
}

