package com.example.api.mbanking.api.accounttype;

import com.example.api.mbanking.api.accounttype.web.AccountTypeDto;
import com.example.api.mbanking.api.accounttype.web.CreateAccountTypeDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AccountTypeService {
    PageInfo<AccountTypeDto> findAll(int page, int limit);
    AccountTypeDto insert(CreateAccountTypeDto createAccountTypeDto);
    AccountTypeDto updatedById(Integer id, AccountTypeDto accountTypeDto);
    Integer serviceDeleteAccountTypeById(Integer id);
    List<AccountTypeDto> selectById(Integer id);
}
