package com.example.api.mbanking.api.accounttype;

import com.example.api.mbanking.api.accounttype.web.AccountTypeDto;
import com.example.api.mbanking.api.user.web.CreateUserDto;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeDto> findAll();
    AccountTypeDto insert(CreateUserDto createUserDto);
    List<AccountTypeDto> selectById(Integer id);
    Integer serviceDeleteAccountTypeById(Integer id);
    AccountTypeDto updatedById(Integer id, AccountTypeDto accountTypeDto);
}
