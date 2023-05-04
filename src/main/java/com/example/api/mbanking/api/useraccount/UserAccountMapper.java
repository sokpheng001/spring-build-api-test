package com.example.api.mbanking.api.useraccount;

import com.example.api.mbanking.api.useraccount.web.CreateUserAccountDto;
import com.example.api.mbanking.api.useraccount.web.UpdateUserAccountIsDisabledDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserAccountMapper {
    @SelectProvider(type = UserAccountProvider.class, method = "buildSelectAllSql")
    @Results(id="resultUserAccountMapper",value = {
            @Result(column = "user_id", property = "userId.id"),
            @Result(column = "account_id",property = "accountId.id"),
            @Result(column = "created_at",property = "createdAt"),
            @Result(column = "is_disabled",property = "isDisabled")
    })
    List<UserAccount> select();
    @InsertProvider(type = UserAccountProvider.class, method = "buildInsertSql")
    @ResultMap("resultUserAccountMapper")
    void insertUserAccount(@Param("a") CreateUserAccountDto createUserAccountDto);
    @Select("SELECT EXISTS(SELECT *FROM mobilebankingapi.public.user_accounts WHERE id = #{id})")
    boolean isEExistedById(@Param("id") Integer id);
    @SelectProvider(type = UserAccountProvider.class, method = "buildSelectSqlById")
    @ResultMap("resultUserAccountMapper")
    UserAccount selectUserAccountById(Integer id);
    @UpdateProvider(type = UserAccountProvider.class, method = "buildUpdateSqlById")
    @ResultMap("resultUserAccountMapper")
    void updateUserAccountIsDisabledById(@Param("id") Integer id, @Param("status") UpdateUserAccountIsDisabledDto updateUserAccountIsDisabledDto);
    @DeleteProvider(type = UserAccountProvider.class, method = "buildDeleteSqlById")
    void deleteUserAccountById(@Param("id") Integer id);
}
