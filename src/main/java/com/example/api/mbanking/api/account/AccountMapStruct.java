package com.example.api.mbanking.api.account;

import com.example.api.mbanking.api.account.web.AccountDto;
import com.example.api.mbanking.api.account.web.CreateAccountDto;
import com.example.api.mbanking.api.account.web.UpdateAccountDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapStruct {
    Account fromCreateAccountDtoToAccount(CreateAccountDto createAccountDto);
    AccountDto fromCreateAccountDtoToAccountDto(CreateAccountDto createAccountDto);
    AccountDto fromAccountToAccountDto(Account account);
    PageInfo<AccountDto> fromPageInfoAccountToPageInfoAccountDto(PageInfo<Account> accountPage);
    List<AccountDto> fromAccountListToAccountDtoList(List<Account> accountList);
    AccountDto frommUpdateAccountDtoToAccountDto(UpdateAccountDto updateAccountDto);
    Account frommUpdateAccountDtoToAccount(UpdateAccountDto updateAccountDto);
}
