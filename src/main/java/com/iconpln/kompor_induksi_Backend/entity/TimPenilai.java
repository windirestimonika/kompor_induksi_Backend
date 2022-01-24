package com.iconpln.kompor_induksi_Backend.entity;

import com.iconpln.kompor_induksi_Backend.constant.Database;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Database.TABLE_MASTER_TIM_PENILAI)
public class TimPenilai extends AuditModel implements Serializable {
    private static final long serialVersionUID = -6231747372662690232L;
    @Column(length = 4)
    private String periodeTahun;
    @Column(length = 30)
    private String noSkTimPenilai;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Database.COLUMN_UNIT)
    private Unit unit;
    @OneToMany(mappedBy = "timPenilai")
    @Where(clause = "is_active=true and is_deleted=false")
    private List<TimPenilaiDetail> timPenilaiDetailList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Database.COLUMN_PERIODE)
    private Periode periode;


}