package com.iconpln.kompor_induksi_Backend.security;

import com.iconpln.kompor_induksi_Backend.entity.User;
import com.iconpln.kompor_induksi_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optional = userService.findByUsername(s);
        if(optional.isPresent()){
            User user = optional.get();
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),new ArrayList<GrantedAuthority>());
        } else {
            return null;
        }
    }

}
