package com.example.api.mbanking.security;

import com.example.api.mbanking.api.auth.AuthMapper;

import com.example.api.mbanking.api.user.Authorities;
import com.example.api.mbanking.api.user.Role;
import com.example.api.mbanking.api.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImp implements UserDetailsService {
    private final AuthMapper authMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authMapper.loadUserByUserName(username).orElseThrow(()
        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found"));
//        User user = authMapper.loadUserByUserName(username);
        CustomerUserDetails customerUserDetails = new CustomerUserDetails();
        customerUserDetails.setUser(user);
        return customerUserDetails;
    }
}
