package com.example.api.mbanking.api.account.web;

import com.example.api.mbanking.api.accounttype.AccountType;
import jakarta.validation.constraints.NotBlank;

public record UpdateAccountDto(
        @NotBlank(message = "Account name is required.")
        String accountName,
        String profile,
        Integer password,
        Integer phoneNumber,
        Double transferLimit,
        AccountType accountType
)
{ };
