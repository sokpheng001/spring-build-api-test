package com.example.api.mbanking.api.account.web;

import com.example.api.mbanking.api.account.AccountProvider;
import com.example.api.mbanking.api.accounttype.AccountType;
import lombok.Builder;

import java.util.List;
public record AccountDto(
        String accountNo,
        String accountName,
        String profile,
        Integer pin,
        Integer password,
        Integer phoneNumber,
        Double transferLimit,
        List<AccountType> accountTypes
){};
