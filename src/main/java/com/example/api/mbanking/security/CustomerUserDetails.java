package com.example.api.mbanking.security;

import com.example.api.mbanking.api.user.Authorities;
import com.example.api.mbanking.api.user.Role;
import com.example.api.mbanking.api.user.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CustomerUserDetails implements UserDetails {
    private User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorityLis = new ArrayList<>();
        for(Role role: user.getRoles()){
            authorityLis.add(new SimpleGrantedAuthority(role.getAuthority()));
            for(Authorities authorities: role.getAuthorities()){
                authorityLis.add(new SimpleGrantedAuthority(authorities.getName()));
            }
        }
        return authorityLis;
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @Override
    public String getUsername() {
        return user.getEmail();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
