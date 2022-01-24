package com.iconpln.kompor_induksi_Backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData implements Serializable {
    private static final long serialVersionUID = -4852628749832491171L;
    private String username;
    private String email;
    private String name;
    private List<String> modules;
}
