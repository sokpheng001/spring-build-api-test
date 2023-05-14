package com.example.api.mbanking.api.auth;

import com.example.api.mbanking.api.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface AuthMapper {
    @InsertProvider(type = AuthProvider.class, method = "buildRegisterSql")
    @Results(id = "resultMapper",value = {
            @Result(column = "verified_code",property = "verifiedCode")
    })
    void register(@Param("u")User user);
    @Select("SELECT *FROM mobilebankingapi.public.users WHERE  mobilebankingapi.public.users.email = #{email} AND is_deleted = FALSE")
    @Result(column = "verified_code",property = "verifiedCode")
    Optional<User> selectByEmail(@Param("email") String email);
    @Select("SELECT EXISTS(SELECT *FROM mobilebankingapi.public.users WHERE verified_code = #{verifiedCode})")
    boolean checkByVerifiedCode(String verifiedCode);
}
