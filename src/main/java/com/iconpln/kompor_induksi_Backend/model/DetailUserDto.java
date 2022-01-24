package com.iconpln.kompor_induksi_Backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailUserDto implements Serializable {
    private static final long serialVersionUID = 735813732585331230L;
    private String id;
    private String username;
    private String role;
    private String nama;
    private String nip;
    private String jabatan;
    private String idUnit;
    private String kodeUnit;
    private String namaUnit;
    private String idUnit1;
    private String kodeUnit1;
    private String namaUnit1;
    private String idUnit2;
    private String kodeUnit2;
    private String namaUnit2;
    private String idUnit3;
    private String kodeUnit3;
    private String namaUnit3;
    private String email;
    private Boolean isActive;
    private Boolean isDeleted;
}
