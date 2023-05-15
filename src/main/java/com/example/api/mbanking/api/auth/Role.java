package com.example.api.mbanking.api.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
public class Role implements GrantedAuthority {
    private Integer id;
    private String name;

    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }
}
