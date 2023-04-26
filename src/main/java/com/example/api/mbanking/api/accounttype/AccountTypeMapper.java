package com.example.api.mbanking.api.accounttype;


import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface AccountTypeMapper {
    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectSql")
    List<AccountType> select();
    @InsertProvider(type = AccountTypeProvider.class, method = "buildInsertSql")
    void insert(@Param("i") AccountType accountType);
}
