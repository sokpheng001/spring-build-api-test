package com.example.api.mbanking.api.account.web;
import com.example.api.mbanking.api.accounttype.AccountType;

import lombok.Builder;
import org.thymeleaf.model.IAttribute;

import java.util.List;


public record AccountDto(
        String accountNo,
        String accountName,
        String profile,
        Integer phoneNumber,
        Double transferLimit,
        AccountType accountType
){};
