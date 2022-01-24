package com.iconpln.kompor_induksi_Backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginSsoDto implements Serializable {
    private static final long serialVersionUID = 8448525959951376715L;
    private String code;
    private String param;
}
