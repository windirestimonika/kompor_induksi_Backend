package com.iconpln.kompor_induksi_Backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = 873048856454511727L;
    private String id;
    private String username;
    private String password;
    private String role;
    private DetailUserDto detailUser;
    private Boolean isActive;
    private Boolean isDeleted;
}
