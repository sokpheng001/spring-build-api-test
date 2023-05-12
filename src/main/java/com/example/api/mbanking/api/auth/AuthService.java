package com.example.api.mbanking.api.auth;

import com.example.api.mbanking.api.auth.web.RegisterDto;

public interface AuthService {
    void register(RegisterDto registerDto);
    void verify(String email);
}
