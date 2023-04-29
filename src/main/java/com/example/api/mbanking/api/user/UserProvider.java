package com.example.api.mbanking.api.user;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.awt.event.MouseWheelListener;

public class UserProvider {

    private static final String tableName = "users";
    public String buildSelectAllSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("is_deleted = FALSE");
        }}.toString();
    }
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("name","#{u.name}");
            VALUES("gender","#{u.gender}");
            VALUES("one_signal_id","#{u.oneSignalId}");
            VALUES("student_card_id","#{u.studentCardId}");
            VALUES("is_student","#{u.isStudent}");
            VALUES("is_deleted","FALSE");
        }}.toString();
    }
//    public String buildDeleteSql(@Param("d") Integer id){
//        return new SQL(){{
//            DELETE_FROM(tableName);
//            WHERE("id =#{id}");
//        }}.toString();
//    }
    public String buildSelectSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id = #{id}","is_deleted = FALSE");
        }}.toString();
    }
    public String deleteById(){
        return new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id = #{id}");
        }}.toString();
    }
    public String buildUpdateIsDeleteByIdSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("is_deleted = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }
    public String UpdateByIdSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("name = #{u.name}");
            SET("gender = #{u.gender}");
            WHERE("id = #{u.id}");
            ORDER_BY("id DESC");
        }}.toString();
    }
    //search by name and studentCardId
    public String searchUserByName(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("name LIKE #{name.name}");
        }}.toString();
    }
    public String searchByStudentCardId(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("name LIKE #{name.name}");
        }}.toString();
    }
}
