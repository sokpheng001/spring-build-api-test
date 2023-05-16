package com.example.api.mbanking.api.auth;

import com.example.api.mbanking.api.auth.web.RegisterDto;

import java.sql.SQLDataException;

public interface AuthService {
    void register(RegisterDto registerDto) throws SQLDataException;
    void verify(String email);
    boolean checkVerifiedCode(String verifiedCode,String email);
}
