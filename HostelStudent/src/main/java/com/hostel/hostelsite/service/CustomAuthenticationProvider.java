package com.hostel.hostelsite.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticationManager manager = new ProviderManager();
            String name = authentication.getName();
            String password = authentication.getCredentials().toString();
            if(password.equals("password")){
                final List<GrantedAuthority> grandedAuth = new ArrayList<>();
                grandedAuth.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
                final UserDetails principal =new User(name,password,grandedAuth);
                final Authentication auth = new UsernamePasswordAuthenticationToken(principal,password,grandedAuth);
                return auth;
            }else{
                return null;
            }
        }
    @Override
    public boolean supports(final Class<?>  authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
