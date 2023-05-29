package com.example.api.mbanking.api.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {
    private Integer id;
    private String name;
    private List<Authorities> authorities;
    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }
}
