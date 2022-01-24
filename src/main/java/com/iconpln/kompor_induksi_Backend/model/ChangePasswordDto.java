package com.iconpln.kompor_induksi_Backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto implements Serializable {
    private static final long serialVersionUID = 1066469788486805195L;
    private String username;
    private String oldPassword;
    private String newPassword;
}
