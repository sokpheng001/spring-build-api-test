package com.example.api.mbanking.api.useraccount;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserAccountMapper {
    @SelectProvider(type = UserAccountProvider.class, method = "buildSelectAllSql")
    @Results(id = "resultMapper",value = {
            @Result(column = "user_id", property = "userId.id"),
            @Result(column = "account_id",property = "accountId.id"),
            @Result(column = "created_at",property = "createdAt"),
            @Result(column = "is_disabled",property = "isDisabled")
    })
    List<UserAccount> select();
}
