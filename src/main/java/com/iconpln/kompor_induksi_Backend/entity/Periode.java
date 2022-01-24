package com.iconpln.kompor_induksi_Backend.entity;

import com.iconpln.kompor_induksi_Backend.constant.Database;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Database.TABLE_MASTER_PERIODE)
public class Periode extends AuditModel implements Serializable {
    private static final long serialVersionUID = 5840992493163088727L;
    @Column(length = 100)
    private String nama;
    private LocalDate tanggalMulai;
    private LocalDate tanggalSelesai;
}
