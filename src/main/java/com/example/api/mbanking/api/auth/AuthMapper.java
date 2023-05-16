package com.example.api.mbanking.api.auth;

import com.example.api.mbanking.api.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface AuthMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "id")
    @InsertProvider(type = AuthProvider.class, method = "buildRegisterSql")
    boolean register(@Param("u")User user);
    @InsertProvider(type = AuthProvider.class, method = "buildInsertUserRoleSql")
    void createUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
    @Select("SELECT * from mobilebankingapi.public.users WHERE email = #{email} AND is_verified = TRUE")
    @Results(id = "resultMapper",value = {
            @Result(column = "id", property = "id"),
            @Result(column = "verified_code",property = "verifiedCode"),
            @Result(column = "is_verified",property = "isVerified"),
            @Result(column = "id", property = "roles",many = @Many(select = "loadUserRoles"))
    })
    Optional<User> loadUserByUserName(@Param("email") String email);
    @SelectProvider(value = AuthProvider.class, method = "buildLoadUserRolesSql")
    List<Role> loadUserRoles(Integer userId);
    @Select("SELECT *FROM mobilebankingapi.public.users WHERE  mobilebankingapi.public.users.email = #{email} AND is_deleted = FALSE")
    @Result(column = "verified_code",property = "verifiedCode")
    Optional<User> selectByEmail(@Param("email") String email);
    @Select("SELECT EXISTS(SELECT *FROM mobilebankingapi.public.users WHERE verified_code = #{verifiedCode} AND email = #{email})")
    boolean checkByVerifiedCode(String verifiedCode, String email);
    @UpdateProvider(type = AuthProvider.class, method = "buildUpdateUserSql")
    void updateIsVerified(@Param("code") String verifiedCode, String email);
    //checking if email is existed
    @Select("SELECT EXISTS(SELECT *FROM mobilebankingapi.public.users WHERE email = #{email})")
    boolean checkEmailIsExisted(String email);
}
