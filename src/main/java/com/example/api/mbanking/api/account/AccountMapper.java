package com.example.api.mbanking.api.account;

import com.example.api.mbanking.api.account.web.SearchAccountByNameDto;
import com.example.api.mbanking.api.account.web.UpdateAccountDto;
import com.example.api.mbanking.api.accounttype.AccountType;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountMapper{
//    @Select(" SELECT accounts.account_no," +
//            " accounts.account_name," +
//            " accounts.profile," +
//            " accounts.phone_number, " +
//            " accounts.transfer_limit" +
//            " FROM mobilebankingapi.public.accounts")
    @SelectProvider(type = AccountProvider.class, method = "buildSelectAllSql")
    @Results(id = "accountResultMap", value = {
            @Result(column = "account_no",property = "accountNo"),
            @Result(column = "account_name",property = "accountName"),
            @Result(column = "profile",property = "profile"),
            @Result(column = "phone_number",property = "phoneNumber"),
            @Result(column = "transfer_limit",property = "transferLimit"),
//            @Result(column = "account_type",property = "accountType.id"),
//            @Result(column = "account_type",property = "accountType.name")
            @Result(column = "account_type", property = "accountType",one = @One(select = "selectAccountType"))
    })
    List<Account> select();
    @InsertProvider(type = AccountProvider.class, method = "buildInsertSql")
    @ResultMap("accountResultMap")
    void insert(@Param("a") Account account);
    @Select("SELECT  *FROM mobilebankingapi.public.account_types WHERE id = #{id}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name",property = "name")
    })
    AccountType selectAccountType(@Param("id") Integer id);
    @SelectProvider(type = AccountProvider.class, method = "buildSelectById")
    @ResultMap("accountResultMap")
    Account selectById(@Param("id") Integer id);
    @UpdateProvider(type = AccountProvider.class, method = "buildUpdateByIdSql")
    @Options(keyColumn = "id", keyProperty = "id")
    void updateById(@Param("id") Integer id, @Param("u") UpdateAccountDto updateAccountDto);
    //delete
    @DeleteProvider(type = AccountProvider.class,method = "buildDeleteByIdSql")
    @ResultMap("accountResultMap")
    void delete(@Param("id") Integer id);
    //isIdExistedById
    @Select("SELECT EXISTS(SELECT *FROM mobilebankingapi.public.accounts WHERE id = #{id})")
    boolean isExistedById(@Param("id") Integer id);
    //isIdExistedByName
    @Select("SELECT EXISTS(SELECT *FROM mobilebankingapi.public.accounts WHERE account_name iLike '%' || #{name} || '%')")
    boolean isExistedByName(@Param("name") String name);
    @SelectProvider(type = AccountProvider.class, method = "buildSearchAccountByName")
    @ResultMap("accountResultMap")
    List<Account> searchAccountByName(@Param("n") SearchAccountByNameDto searchAccountByNameDto);
}
