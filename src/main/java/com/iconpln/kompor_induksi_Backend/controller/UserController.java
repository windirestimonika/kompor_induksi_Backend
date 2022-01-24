package com.iconpln.kompor_induksi_Backend.controller;

import com.iconpln.kompor_induksi_Backend.entity.User;
import com.iconpln.kompor_induksi_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;
}
