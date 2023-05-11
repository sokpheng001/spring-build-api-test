package com.example.api.mbanking.api.useraccount;

import com.example.api.mbanking.api.account.Account;
import com.example.api.mbanking.api.accounttype.AccountType;
import com.example.api.mbanking.api.user.User;
import com.example.api.mbanking.api.useraccount.web.CreateUserAccountDto;
import com.example.api.mbanking.api.useraccount.web.UpdateUserAccountIsDisabledDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserAccountMapper {
    @SelectProvider(type = UserAccountProvider.class, method = "buildSelectAllSql")
    @Results(id="resultUserAccountMapper",value = {
            @Result(column = "user_id", property = "userId",one = @One(select = "selectUser")),
            @Result(column = "account_id",property = "accountId", one = @One(select="selectAccount")),
            @Result(column = "created_at",property = "createdAt"),
            @Result(column = "is_disabled",property = "isDisabled")
    })
    List<UserAccount> selectAll();
    //relational
    @Select("SELECT *FROM mobilebankingapi.public.users WHERE id = #{id}")
    @Results(value = {
            @Result(column = "one_signal_id",property = "oneSignalId"),
            @Result(column = "is_deleted", property = "isDeleted"),
            @Result(column = "is_student", property = "isStudent"),
            @Result(column = "student_card_id", property = "studentCardId")
    })
    User selectUser(@Param("id") Integer id);
    @Select("SELECT *FROM mobilebankingapi.public.accounts WHERE id = #{id}")
    @Results(value = {
            @Result(column = "account_no" ,property = "accountNo"),
            @Result(column = "account_name", property = "accountName"),
            @Result(column = "phone_number",property = "phoneNumber"),
            @Result(column = "transfer_limit",property = "transferLimit"),
            @Result(column = "account_type", property = "accountType",one = @One(select = "selectAccountType"))
    })
    Account selectAccount(@Param("id") Integer id);
    @Select("SELECT  *FROM mobilebankingapi.public.account_types WHERE id = #{id}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "name",property = "name")
    })
    AccountType selectAccountType(@Param("id") Integer id);
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
