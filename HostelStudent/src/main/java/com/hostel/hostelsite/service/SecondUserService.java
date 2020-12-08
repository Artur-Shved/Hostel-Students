package com.hostel.hostelsite.service;

import com.hostel.hostelsite.dao.Role;
import com.hostel.hostelsite.dao.entity.User;
import com.hostel.hostelsite.repo.UserDataRepository;
import com.hostel.hostelsite.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SecondUserService implements AuthenticationProvider {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDataRepository repository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = repository.findByUsername("user");
        user.setActive(true);
        user.setRole(Collections.singleton(Role.ROLE_USER));
        if(bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(name, password, user.getRole());
        }else if(password.equals("admin")){
            return new UsernamePasswordAuthenticationToken(name,password, Collections.singleton(Role.ROLE_SUPER_ADMIN));
        }
        else {
            throw new BadCredentialsException("External system authentication fail");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
