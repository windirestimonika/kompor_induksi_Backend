package com.iconpln.kompor_induksi_Backend.service.serviceImpl;

import com.iconpln.kompor_induksi_Backend.assembler.DetailUserAssembler;
import com.iconpln.kompor_induksi_Backend.assembler.UserAssembler;
import com.iconpln.kompor_induksi_Backend.entity.User;
import com.iconpln.kompor_induksi_Backend.model.ChangePasswordDto;
import com.iconpln.kompor_induksi_Backend.model.JwtDto;
import com.iconpln.kompor_induksi_Backend.model.JwtRequest;
import com.iconpln.kompor_induksi_Backend.model.UserDto;
import com.iconpln.kompor_induksi_Backend.repository.UserRepo;
import com.iconpln.kompor_induksi_Backend.security.JwtTokenUtil;
import com.iconpln.kompor_induksi_Backend.security.JwtUserDetailsService;
import com.iconpln.kompor_induksi_Backend.service.DetailUserService;
import com.iconpln.kompor_induksi_Backend.exception.UserAlreadyExistsException;
import com.iconpln.kompor_induksi_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static io.github.perplexhub.rsql.RSQLJPASupport.toSpecification;

@Service
public class UserResponse implements UserService {
    @Autowired
    UserRepo repo;
    @Autowired
    DetailUserService detailUserService;
    @Autowired
    UserAssembler assembler;
    @Autowired
    DetailUserAssembler detailUserAssembler;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public JwtDto login(JwtRequest loginDto) throws DisabledException, BadCredentialsException {
        authenticate(loginDto.getUsername(), loginDto.getPassword());
        return generateJwtDto(loginDto.getUsername());
    }

    @Override
    @Transactional
    public void register(UserDto dto) {
        String filter = "username=='"+dto.getUsername()+"';isActive==true;isDeleted==false";
        Optional<User> optional = repo.findOne(toSpecification(filter));
        if(optional.isPresent()){
            throw new UserAlreadyExistsException("Username "+dto.getUsername()+" sudah terdaftar");
        }
        User user = assembler.toEntity(dto);
        user.setPassword(encryptPassword(dto.getPassword()));
        if (dto.getDetailUser() != null) {
            user.setDetailUserEntity(detailUserService.saveEntity(dto.getDetailUser()));
        }
        repo.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repo.findByUsernameAndIsActiveTrueAndIsDeletedFalse(username);
    }

    @Override
    @Transactional
    public JwtDto changePassword(ChangePasswordDto dto) {
        authenticate(dto.getUsername(), dto.getOldPassword());
        Optional<User> optional = repo.findByUsernameAndIsActiveTrueAndIsDeletedFalse(dto.getUsername());
        if (optional.isPresent()) {
            User user = optional.get();
            user.setPassword(encryptPassword(dto.getNewPassword()));
            repo.save(user);
            return generateJwtDto(user.getUsername());
        } else {
            throw new DisabledException("User inactive");
        }
    }

    @Override
    public String getFullNameByUsername(String username) {
        Optional<User> optional = findByUsername(username);
        if (optional.isPresent()) {
            return optional.get().getDetailUserEntity().getNama();
        } else {
            return "";
        }
    }

    private String encryptPassword(String plainPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        return encoder.encode(plainPassword);
    }

    private void authenticate(String username, String password) throws DisabledException, BadCredentialsException{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    public JwtDto generateJwtDto(String username) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        return new JwtDto(token, refreshToken, null);
    }
}