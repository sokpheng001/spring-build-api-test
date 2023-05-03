package com.example.api.mbanking.api.account;

import com.example.api.mbanking.api.account.web.AccountDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapStruct {
    Account fromAccountDtoToAccount(AccountDto accountDto);
    List<AccountDto> fromAccountDtoToAccountTypeDto(List<Account> accountList);
}
