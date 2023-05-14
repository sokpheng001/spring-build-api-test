package com.example.api.mbanking.api.auth.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AuthController {
    @GetMapping("/api/v1/auth/ch")
    public String checkVerified(){
        return null;
    }
}
