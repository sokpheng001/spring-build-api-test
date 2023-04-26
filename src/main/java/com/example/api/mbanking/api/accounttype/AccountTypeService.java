package com.example.api.mbanking.api.accounttype;

import com.example.api.mbanking.api.accounttype.web.AccountTypeDto;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeDto> findAll();
    List<AccountTypeDto> insert(AccountTypeDto accountTypeDto);
    List<AccountTypeDto> delete(Integer id);
}
