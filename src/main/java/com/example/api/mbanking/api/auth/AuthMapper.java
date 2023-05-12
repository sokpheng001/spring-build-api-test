package com.example.api.mbanking.api.auth;

import com.example.api.mbanking.api.user.User;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AuthMapper {
    @InsertProvider(type = AuthProvider.class, method = "buildRegisterSql")
    void register(@Param("u")User user);
    @Select("SELECT *FROM mobilebankingapi.public.users WHERE  mobilebankingapi.public.users.email = #{email} AND is_deleted = FALSE")
    User selectByEmail(@Param("email") String email);
}
