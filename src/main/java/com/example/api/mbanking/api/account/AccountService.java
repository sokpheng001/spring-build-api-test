package com.example.api.mbanking.api.account;

import com.example.api.mbanking.api.account.web.AccountDto;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    AccountDto insert(AccountDto accountDto);
}
