package com.example.api.mbanking.api.useraccount.web;

import com.example.api.mbanking.api.account.Account;
import com.example.api.mbanking.api.user.User;
import lombok.Builder;

import java.util.Date;
import java.util.List;


@Builder
public record UserAccountDto(
        List<User> userId,
        List<Account> accountId,
        Date createdAt,
        Boolean isDisabled
) { };
