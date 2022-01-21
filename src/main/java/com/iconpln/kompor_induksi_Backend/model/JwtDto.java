package com.iconpln.kompor_induksi_Backend.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtDto implements Serializable {
    private static final long serialVersionUID = 8327592968862903308L;
    private String jwtToken;
    private String refreshToken;
    private String logoutUrl;
}
