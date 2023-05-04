package com.example.api.mbanking.api.account.web;

import com.example.api.mbanking.api.accounttype.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
@Builder
public record CreateAccountDto(
        @NotBlank(message = "Account No. is required.")
        String accountNo,
        @NotBlank(message = "Account name is required.")
        String accountName,
        @NotNull(message = "Pin is required.")
        Integer pin,
        @NotNull
        Integer password,
        String profile,
        @NotNull(message = "Phone number is required.")
        Integer phoneNumber,
        Double transferLimit,
        AccountType accountType
){};
