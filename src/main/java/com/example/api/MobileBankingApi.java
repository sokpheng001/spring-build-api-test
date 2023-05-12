package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class MobileBankingApi {
    public static void main(String[] args) {
        SpringApplication.run(MobileBankingApi.class, args);
    }
    @GetMapping("/test")
    public String test(){
        return "auth/verify";
    }
}
