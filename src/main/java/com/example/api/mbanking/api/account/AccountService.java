package com.example.api.mbanking.api.account;

import com.example.api.mbanking.api.account.web.AccountDto;
import com.example.api.mbanking.api.account.web.CreateAccountDto;
import com.example.api.mbanking.api.account.web.SearchAccountByNameDto;
import com.example.api.mbanking.api.account.web.UpdateAccountDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    PageInfo<AccountDto> selectAll(int page, int limit);
    AccountDto createAccount(CreateAccountDto createAccountDto);
    AccountDto searchAccountById(Integer id);
    AccountDto updateAccountById(Integer id, UpdateAccountDto updateAccountDto);
    Integer deleteAccountById(Integer id);
    List<AccountDto> searchByAccountName(SearchAccountByNameDto searchAccountByNameDto);
}
