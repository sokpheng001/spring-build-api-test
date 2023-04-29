package com.example.api.mbanking.api.accounttype;

import com.example.api.mbanking.api.accounttype.web.AccountTypeDto;
import com.example.api.mbanking.api.user.web.CreateUserDto;
import org.mapstruct.Mapper;

import java.security.cert.CRLException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapStruct{
    List<AccountTypeDto> toDtoList(List<AccountType> model);
    AccountType fromCreateAccountDtotoAccountType(CreateUserDto createUserDto);
    AccountTypeDto fromAccountTypeToAccountTypeDto(AccountType accountType);
    AccountType toAccountType(AccountTypeDto accountTypeDto);
}