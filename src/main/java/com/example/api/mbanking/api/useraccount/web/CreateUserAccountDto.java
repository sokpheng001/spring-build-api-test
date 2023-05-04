package com.example.api.mbanking.api.useraccount.web;

import com.example.api.mbanking.api.account.Account;
import com.example.api.mbanking.api.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Date;

@Builder
public record CreateUserAccountDto(
        User userId,
        @NotNull(message = "AccountId is required.")
        Account accountId,
        Date createdAt,
        Boolean isDisabled
){};
