package com.example.api.mbanking.api.auth.web;

import com.example.api.mbanking.api.auth.AuthService;
import com.example.api.mbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthService authService;

    @GetMapping("/check-verify")
    public BaseRest<?> checkVerifiedCode(@RequestParam(required = false) String email, @RequestParam(required = false) String verifiedCode){
        String message;
        if(authService.checkVerifiedCode(verifiedCode,email)){
            message = "Verified successfully.";
        }else {
            message = "Failed to verify";
        }
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message(message)
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }
    @PostMapping("/register")
    public BaseRest<?> register(@Valid @RequestBody RegisterDto registerDto) throws SQLDataException {
        authService.register(registerDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have registered successfully.")
                .timestamp(LocalDateTime.now())
                .data(registerDto.email())
                .build();
    }
    @PostMapping("/verify")
    public BaseRest<?> verify(@RequestParam String email){
        authService.verify(email);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Please check email to verify.")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }
    @PostMapping("/login")
    public BaseRest<?> login(@RequestBody @Valid LoginDto loginDto){
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Login successfully")
                .timestamp(LocalDateTime.now())
                .data(authService.login(loginDto))
                .build();
    }
}
