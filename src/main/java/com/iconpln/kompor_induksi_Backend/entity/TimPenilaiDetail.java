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
@Table(name = Database.TABLE_MASTER_TIM_PENILAI_DETAIL)
public class TimPenilaiDetail extends AuditModel implements Serializable {

    private static final long serialVersionUID = -7525454902319581107L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Database.COLUMN_TIM_PENILAI)
    private TimPenilai timPenilai;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Database.COLUMN_INDIKATOR)
    private Indikator indikator;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Database.COLUMN_PENILAI)
    private DetailUser penilai;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Database.COLUMN_SK)
    private SuratKeputusan suratKeputusan;
}
