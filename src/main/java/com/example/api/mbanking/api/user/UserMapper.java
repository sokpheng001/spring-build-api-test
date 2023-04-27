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
    @DeleteProvider(type = UserProvider.class, method = "buildDeleteSql")
    void delete(Integer id);
    @SelectProvider(type = UserProvider.class, method = "buildSelectSql")
    @Result(column = "student_card_id", property = "studentCardId")
    @Result(column = "is_student",property = "isStudent")
    Optional<User> selectById(@Param("id") Integer id);
}
