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
    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectByIdSql")
    List<AccountType> selectById(@Param("id") Integer id);
    @Select("SELECT EXISTS(SELECT *FROM mobilebankingapi.public.account_types WHERE id = #{id})")
    boolean isExisted(@Param("id") Integer id);
    @DeleteProvider(type = AccountTypeProvider.class, method = "buildDeleteByIdSql")
    void deleteAccountTypesById(@Param(("id")) Integer id);
    @UpdateProvider(type = AccountTypeProvider.class, method = "buildUpdateByIdSql")
    void updateAccountTypeById(@Param("id") Integer id,@Param("accountType") AccountTypeDto accountTypeDto);
}
