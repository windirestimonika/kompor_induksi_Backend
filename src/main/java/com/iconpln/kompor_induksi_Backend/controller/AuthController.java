package com.iconpln.kompor_induksi_Backend.controller;

import com.iconpln.kompor_induksi_Backend.entity.User;
import com.iconpln.kompor_induksi_Backend.model.ChangePasswordDto;
import com.iconpln.kompor_induksi_Backend.model.JwtDto;
import com.iconpln.kompor_induksi_Backend.model.JwtRequest;
import com.iconpln.kompor_induksi_Backend.model.LoginSsoDto;
import com.iconpln.kompor_induksi_Backend.security.JwtTokenUtil;
import com.iconpln.kompor_induksi_Backend.security.JwtUserDetailsService;
import com.iconpln.kompor_induksi_Backend.service.AuthService;
import com.iconpln.kompor_induksi_Backend.service.UserService;
import com.iconpln.kompor_induksi_Backend.service.serviceImpl.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest request) throws Exception {
        try{
            JwtDto response = userService.login(request);
            return ResponseEntity.ok(response);
        } catch (DisabledException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse.unauthorized("User Disabled"));
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse.unauthorized("Invalid Username/Password"));
        }
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto dto) throws Exception {
        try{
            JwtDto response = userService.changePassword(dto);
            return ResponseEntity.ok(response);
        } catch (DisabledException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse.unauthorized("User Disabled"));
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse.unauthorized("Invalid Username/Password"));
        }
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<?> refresh(@RequestHeader(name = "Authorization") String oldToken) {
        try{
            JwtDto response = jwtTokenUtil.refreshToken(oldToken);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse.unauthorized(e.getMessage()));
        }
    }

    @GetMapping(value = "/sso")
    public ResponseEntity<?> sso(@RequestParam(defaultValue = "non-local") String param){
        try{
            return ResponseEntity.ok(authService.sso(param));
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse.unauthorized("unauthorized"));
    }

    @PostMapping(value = "/login-sso")
    public ResponseEntity<?> loginSso(@RequestBody LoginSsoDto request) throws Exception {
        try{
            String param = "";
            if(request.getParam()!=null){
                param = request.getParam();
            }
            return ResponseEntity.ok().body(authService.loginWithCode(request.getCode(), param));
        } catch (DisabledException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse.unauthorized("User Disabled"));
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse.unauthorized("Invalid Username/Password"));
        }
    }

}
