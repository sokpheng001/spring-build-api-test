package com.example.api.mbanking.util;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    private BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    public UserDetails userDetails(String name, String password, String roles){
        return User.builder()
                .username(name)
                .password(encoder().encode(password))
                .roles(roles)
                .build();
    }
}
