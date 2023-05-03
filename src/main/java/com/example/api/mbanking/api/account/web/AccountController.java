package com.example.api.mbanking.api.account.web;


import com.example.api.mbanking.api.account.AccountService;
import com.example.api.mbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @GetMapping
    public BaseRest<?> selectAllAccount(){
        System.out.println(accountService.selectAll());
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Accounts has been selected successfully")
                .timestamp(LocalDateTime.now())
                .data(accountService.selectAll())
                .build();
    }
    @PostMapping("/account-created")
    public BaseRest<?> insertAccount(@RequestBody AccountDto accountDto){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account has been created successfully")
                .timestamp(LocalDateTime.now())
                .data(accountService.createAccount(accountDto))
                .build();
    }
}
