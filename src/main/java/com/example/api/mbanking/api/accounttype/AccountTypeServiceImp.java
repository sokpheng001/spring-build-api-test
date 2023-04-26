package com.example.api.mbanking.api.accounttype;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImp implements AccountTypeService{
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeDtos;
    @Override
    public List<AccountTypeDto> findAll() {
        List<AccountType> accountTypes = accountTypeMapper.select();
        System.out.println("This is " + accountTypes);
//        List<AccountTypeDto> accountTypeDtos = accountTypes
//                .stream()
//                .map(accountType -> new AccountTypeDto(accountType.getName())).collect(Collectors.toList());
        return accountTypeDtos.toDtoList(accountTypes);
    }

    @Override
    public List<AccountTypeDto> insert() {
        return null;
    }

}
