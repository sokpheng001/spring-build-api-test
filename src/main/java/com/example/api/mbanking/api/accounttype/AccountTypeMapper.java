package com.example.api.mbanking.api.accounttype;


import com.example.api.mbanking.api.accounttype.web.AccountTypeDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountTypeMapper {
    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectSql")
    List<AccountType> select();
    @InsertProvider(type = AccountTypeProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
    void insert(@Param("n") AccountType accountType);
    @DeleteProvider(type = AccountTypeProvider.class, method = "buildDeleteSql")
    void delete(@Param("i") Integer id);
}
