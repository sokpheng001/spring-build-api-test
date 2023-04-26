package com.example.api.mbanking.api.accounttype;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeDto> findAll();
    List<AccountTypeDto> insert();
}
