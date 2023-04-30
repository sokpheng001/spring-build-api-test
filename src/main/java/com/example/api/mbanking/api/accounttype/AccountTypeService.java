package com.example.api.mbanking.api.accounttype;

import com.example.api.mbanking.api.accounttype.web.AccountTypeDto;
import com.example.api.mbanking.api.accounttype.web.CreateAccountTypeDto;
import com.example.api.mbanking.api.user.web.CreateUserDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AccountTypeService {
    PageInfo<AccountTypeDto> findAll(int page, int limit);
    AccountTypeDto insert(CreateAccountTypeDto createAccountTypeDto);
    List<AccountTypeDto> selectById(Integer id);
    Integer serviceDeleteAccountTypeById(Integer id);
    AccountTypeDto updatedById(Integer id, AccountTypeDto accountTypeDto);
}
