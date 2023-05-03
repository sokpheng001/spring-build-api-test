package com.example.api.mbanking.api.account;

import com.example.api.mbanking.api.account.web.AccountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    List<AccountDto> selectAll();
    AccountDto createAccount(AccountDto accountDto);
}
