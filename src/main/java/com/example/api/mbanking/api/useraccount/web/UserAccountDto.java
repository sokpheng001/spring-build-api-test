package com.example.api.mbanking.api.useraccount.web;

import com.example.api.mbanking.api.account.Account;
import com.example.api.mbanking.api.user.User;
import lombok.Builder;

import java.util.Date;
import java.util.List;



public record UserAccountDto(
        User userId,
        Account accountId,
        Date createdAt,
        Boolean isDisabled
) { };
