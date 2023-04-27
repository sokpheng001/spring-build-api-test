package com.example.api.mbanking.api.accounttype;

import com.example.api.mbanking.api.accounttype.web.AccountTypeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapStruct{
    List<AccountTypeDto> toDtoList(List<AccountType> model);
    AccountTypeDto toDto(AccountType model);
    AccountType toAccountType(AccountTypeDto accountTypeDto);
}