package com.example.api.mbanking.api.useraccount.web;

import com.example.api.mbanking.api.useraccount.UserAccountService;
import com.example.api.mbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/user-accounts")
@RequiredArgsConstructor
public class UserAccountRestController {
    private final UserAccountService userAccountService;
    @GetMapping
    public BaseRest<?> selectAll(){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Accounts has been selected successfully")
                .timestamp(LocalDateTime.now())
                .data(userAccountService.selectAll())
                .build();
    }
}
