package com.example.api.mbanking.api.user;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface UserMapper {
    @InsertProvider(type = UserProvider.class , method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
    void insert(@Param("u") User user);
    @SelectProvider(type = UserProvider.class, method = "buildSelectSql")
    @Result(column = "student_card_id", property = "studentCardId")
    @Result(column = "is_student",property = "isStudent")
    Optional<User> selectById(@Param("id") Integer id);
    //
    @Select("SELECT EXISTS(SELECT *FROM mobilebankingapi.public.users WHERE id = #{id})")
    boolean existById(@Param("id") Integer id);
    @DeleteProvider(type = UserProvider.class, method = "deleteById")
    void deleteById(@Param("id") Integer id);
    @UpdateProvider(type = UserProvider.class, method = "buildUpdateIsDeleteByIdSql")
    void updateIsDeletedById(@Param("id") Integer id, @Param("status") boolean status);
}
