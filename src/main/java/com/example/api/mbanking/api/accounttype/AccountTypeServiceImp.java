package com.example.api.mbanking.api.accounttype;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImp implements AccountTypeService{
    private final AccountTypeMapper accountTypeMapper;
    @Override
    public List<AccountTypeDto> findAll() {
        accountTypeMapper.insert();
        List<AccountType> accountTypes = accountTypeMapper.select();
        List<AccountTypeDto> accountTypeDtos = accountTypes
                .stream()
                .map(accountType -> new AccountTypeDto(accountType.getName())).collect(Collectors.toList());
        return accountTypeDtos;
    }
}
