package com.example.api.mbanking.api.user;

import com.example.api.mbanking.api.user.web.StudentCardIDDto;
import com.example.api.mbanking.api.user.web.UserDtoSearchByName;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UserMapper {
    @SelectProvider(type = UserProvider.class, method = "buildSelectAllSql")
    @Results(id = "userResultMap", value = {
            @Result (column = "student_card_id", property = "studentCardId"),
            @Result(column = "is_student",property = "isStudent")
    })
    List<User> select();
    @InsertProvider(type = UserProvider.class , method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
    void insert(@Param("u") User user);
    @SelectProvider(type = UserProvider.class, method = "buildSelectSql")
//    @Result(column = "student_card_id", property = "studentCardId")
//    @Result(column = "is_student",property = "isStudent")
    //
    @ResultMap("userResultMap")
    Optional<User> selectById(@Param("id") Integer id);
    //
    @Select("SELECT EXISTS(SELECT *FROM mobilebankingapi.public.users WHERE id = #{id})")
    boolean existById(@Param("id") Integer id);
    @Select("SELECT EXISTS(SELECT *FROM mobilebankingapi.public.users WHERE student_card_id = #{cardId})")
    boolean existByStudentCardId(@Param("cardId") String cardId);
    @Select("SELECT EXISTS(SELECT *FROM mobilebankingapi.public.users WHERE name ilike '%' || #{name} || '%')")
    boolean existByUserName(@Param("name") String name);
    @DeleteProvider(type = UserProvider.class, method = "deleteById")
    void deleteById(@Param("id") Integer id);
    @UpdateProvider(type = UserProvider.class, method = "buildUpdateIsDeleteByIdSql")
    void updateIsDeletedById(@Param("id") Integer id, @Param("status") boolean status);
    @UpdateProvider(type = UserProvider.class, method = "UpdateByIdSql")
    void updateById(@Param("u") User user);
    //search by name and STUDENT CARD ID
    @SelectProvider(type = UserProvider.class, method = "searchUserByName")
    @ResultMap("userResultMap")
    List<User> searchUserByName(@Param("name")UserDtoSearchByName searchByName);
    @SelectProvider(type = UserProvider.class, method = "searchUserByStudentCardId")
    @ResultMap("userResultMap")
    List<User> searchUserByStudentCardId(@Param("cardId") StudentCardIDDto studentCardIDDto);
}
