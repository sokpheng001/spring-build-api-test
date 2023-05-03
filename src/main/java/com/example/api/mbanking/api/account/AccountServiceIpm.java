package com.example.api.mbanking.api.account;

import com.example.api.mbanking.api.account.web.AccountDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountServiceIpm implements AccountService{
    private final AccountMapper accountMapper;
    private final AccountMapStruct accountMapStruct;

    @Override
    public List<AccountDto> selectAll() {
        return accountMapStruct.fromAccountDtoToAccountTypeDto(accountMapper.select());
    }
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = accountMapStruct.fromAccountDtoToAccount(accountDto);
        System.out.println(account.getAccountTypes().get(0).getId());
        accountMapper.insert(account);
        return accountDto;
    }
}
