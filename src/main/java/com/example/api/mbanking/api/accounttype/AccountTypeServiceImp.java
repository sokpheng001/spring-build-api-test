package com.example.api.mbanking.api.accounttype;

import com.example.api.mbanking.api.accounttype.web.AccountTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImp implements AccountTypeService{
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeDtos;
    private final AccountTypeMapStruct accountTypeMapStruct;
    @Override
    public List<AccountTypeDto> findAll() {
        List<AccountType> accountTypes = accountTypeMapper.select();
        System.out.println("This is " + accountTypes);
        System.out.println(accountTypes.get(0).getId());
//        List<AccountTypeDto> accountTypeDtos = accountTypes
//                .stream()
//                .map(accountType -> new AccountTypeDto(accountType.getName())).collect(Collectors.toList());
        return accountTypeDtos.toDtoList(accountTypes);
//        return accountTypeDtos;
    }

    @Override
    public List<AccountTypeDto> insert(AccountTypeDto accountTypeDto) {
        AccountType accountType  = accountTypeMapStruct.toAccountType(accountTypeDto);
        accountTypeMapper.insert(accountType);
        System.out.println("Last Id: " + accountType.getId());
        List<AccountType> accountTypes = accountTypeMapper.select();
        return accountTypeMapStruct.toDtoList(accountTypes);
    }
    @Override
    public List<AccountTypeDto> delete(Integer id) {
        accountTypeMapper.delete(id);
        List<AccountType> accountTypeList1 = accountTypeMapper.select();
        return accountTypeMapStruct.toDtoList(accountTypeList1);
    }
}
