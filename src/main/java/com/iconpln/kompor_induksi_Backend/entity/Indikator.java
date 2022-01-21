package com.iconpln.kompor_induksi_Backend.entity;

import com.iconpln.kompor_induksi_Backend.constant.Database;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Database.TABLE_MASTER_INDIKATOR)
public class Indikator extends AuditModel implements Serializable {

    @Column(length = 30)
    private String nama;
    private Integer urutan;
    private BigDecimal bobot;
}
