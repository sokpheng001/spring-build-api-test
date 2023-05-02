package com.example.api.mbanking.api.account;

import com.example.api.mbanking.api.account.web.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapStruct {
    Account fromAccountDtoToAccount(AccountDto accountDto);
}
