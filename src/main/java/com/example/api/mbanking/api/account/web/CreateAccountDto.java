package com.example.api.mbanking.api.account.web;

import com.example.api.mbanking.api.accounttype.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;
@Builder
public record CreateAccountDto(
        String accountNo,
        @NotBlank(message = "Account name is required.")
        String accountName,
        Integer pin,
        @NotBlank(message = "Password is required.")
        Integer password,
        String profile,
        @NotBlank(message = "Phone number is required.")
        Integer phoneNumber,
        Double transferLimit,
        AccountType accountType
){};
