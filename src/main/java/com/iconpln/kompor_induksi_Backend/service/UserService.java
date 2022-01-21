package com.iconpln.kompor_induksi_Backend.service;

import com.iconpln.kompor_induksi_Backend.entity.User;
import com.iconpln.kompor_induksi_Backend.model.JwtDto;
import com.iconpln.kompor_induksi_Backend.model.JwtRequest;
import com.iconpln.kompor_induksi_Backend.model.UserDto;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;

import java.util.Optional;

public interface UserService {
    JwtDto login(JwtRequest loginDto) throws DisabledException, BadCredentialsException;
    void register(UserDto dto);
    Optional<User> findByUsername(String username);
    JwtDto changePassword(ChangePasswordDto dto);
    String getFullNameByUsername(String username);
    JwtDto generateJwtDto(String username);
}