package com.example.api.mbanking.api.account.web;

import com.example.api.mbanking.api.accounttype.AccountType;
import lombok.Builder;

@Builder
public record AccountDto(
        String accountNo,
        String accountName,
        String profile,
        Integer phoneNumber,
        Double transferLimit,
        AccountType accountType
){};
