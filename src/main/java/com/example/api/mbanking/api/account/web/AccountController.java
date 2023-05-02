package com.example.api.mbanking.api.account.web;


import com.example.api.mbanking.api.account.AccountService;
import com.example.api.mbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @PostMapping("/add")
    public BaseRest<?> insertAccount(@RequestBody AccountDto accountDto){
        System.out.println(accountDto);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account has been created successfully")
                .timestamp(LocalDateTime.now())
                .data(accountService.insert(accountDto))
                .build();
    }
}
