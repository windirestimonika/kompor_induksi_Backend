package com.iconpln.kompor_induksi_Backend.controller;

import com.iconpln.kompor_induksi_Backend.model.UserDto;
import com.iconpln.kompor_induksi_Backend.exception.UserAlreadyExistsException;
import com.iconpln.kompor_induksi_Backend.service.UserService;
import com.iconpln.kompor_induksi_Backend.service.serviceImpl.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "User Controller")
@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService service;

    @ApiOperation(value = "Register")
    @PostMapping("/register")
    public ResponseEntity<Object> save (@RequestBody UserDto dto){
        try{
            service.register(dto);
            return ResponseEntity.status(200).body(BaseResponse.ok("Register successfull"));
        } catch (UserAlreadyExistsException e){
            return ResponseEntity.status(500).body(BaseResponse.error(e.getMessage()));
        } catch (Exception e){
            return ResponseEntity.status(500).body(BaseResponse.error("Failed register. "+e.getMessage()));
        }
    }
}