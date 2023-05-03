package com.example.api.mbanking.api.account;

import com.example.api.mbanking.api.accounttype.AccountType;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountMapper{
//    @SelectProvider(type = AccountProvider.class, method = "buildSelectAllSql")
    @Select("SELECT accounts.account_no," +
            " accounts.account_name," +
            " accounts.profile," +
            " accounts.phone_number, " +
            " accounts.transfer_limit" +
            " FROM mobilebankingapi.public.accounts" +
            " LEFT JOIN mobilebankingapi.public.account_types " +
            " on mobilebankingapi.public.account_types.id = mobilebankingapi.public.accounts.account_type")
    @Results(id = "accountResultMap", value = {
            @Result(column = "account_no",property = "accountNo"),
            @Result(column = "account_name",property = "accountName"),
            @Result(column = "profile",property = "profile"),
            @Result(column = "phone_number",property = "phoneNumber"),
            @Result(column = "transfer_limit",property = "transferLimit"),
//            @Result(column = "account_type",property = "accountTypes.get(0).getId()")
    })
    List<Account> select();
    @InsertProvider(type = AccountProvider.class, method = "buildInsertSql")
    @ResultMap("accountResultMap")
    void insert(@Param("a") Account account);
}
