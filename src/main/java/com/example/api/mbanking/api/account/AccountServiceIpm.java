package com.example.api.mbanking.api.account;

import com.example.api.mbanking.api.account.web.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceIpm implements AccountService{
    private final AccountMapper accountMapper;
    private final AccountMapStruct accountMapStruct;
    @Override
    public AccountDto insert(AccountDto accountDto) {
        Account account = accountMapStruct.fromAccountDtoToAccount(accountDto);
        System.out.println(account);
        accountMapper.insert(account);
        return accountDto;
    }
}
