package com.example.api.mbanking.api.auth;

import com.example.api.mbanking.api.auth.web.AuthDto;
import com.example.api.mbanking.api.auth.web.LoginDto;
import com.example.api.mbanking.api.auth.web.RegisterDto;

import java.sql.SQLDataException;

public interface AuthService {
    void register(RegisterDto registerDto) throws SQLDataException;
    void verify(String email);
    boolean checkVerifiedCode(String verifiedCode,String email);
    AuthDto login(LoginDto loginDto);
}
