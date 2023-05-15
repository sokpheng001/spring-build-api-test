package com.example.api.mbanking.api.auth;

import org.apache.ibatis.jdbc.SQL;

public class AuthProvider {
    public String buildRegisterSql(){
        return new SQL(){{
            INSERT_INTO("users");
            VALUES("email","#{u.email}");
            VALUES("password","#{u.password}");
            VALUES("is_verified","#{u.isVerified}");
            VALUES("verified_code","#{u.verifiedCode}");
            VALUES("is_deleted","FALSE");
        }}.toString();
    }
    public String buildInsertUserRoleSql(){
        return new SQL(){{
            INSERT_INTO("users_roles");
            VALUES("user_id","#{userId}");
            VALUES("role_id","#{roleId}");
        }}.toString();
    }
    public String buildLoadUserRolesSql(){
        return new SQL(){{
            SELECT("r.id, r.name");
            FROM("roles AS r");
            INNER_JOIN("user_roles AS ur ON r.id = ur.role_id");
            WHERE("r.id = #{id}");
        }}.toString();
    }
    public String buildUpdateUserSql(){
        return new SQL(){{
            UPDATE("users");
            SET("verified_code = #{code}","is_verified = TRUE");
            WHERE("email = #{email}");
        }}.toString();
    }
}


