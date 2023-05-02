package com.example.api.mbanking.api.account;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountMapper{
    @InsertProvider(type = AccountProvider.class, method = "buildInsertSql")
    void insert(@Param("a") Account account);
}
