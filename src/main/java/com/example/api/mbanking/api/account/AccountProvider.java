package com.example.api.mbanking.api.account;

import org.apache.ibatis.jdbc.SQL;

public class AccountProvider {
    private final String tableName = "accounts";
    private final String getTableName = "account_types";
    public String buildSelectAllSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
        }}.toString();
    }
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("account_no","#{a.accountNo}");
            VALUES("account_name","#{a.accountName}");
            VALUES("profile","#{a.profile}");
            VALUES("pin","#{a.pin}");
            VALUES("password","#{a.password}");
            VALUES("phone_number","#{a.phoneNumber}");
            VALUES("transfer_limit","#{a.transferLimit}");
            VALUES("account_type","#{a.accountType.id}");
        }}.toString();
    }
    public String buildSelectById(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
//            JOIN(getTableName + " ON accounts.account_type = account_types.id");
            WHERE("id = #{id}");
        }}.toString();
    }
    public String buildDeleteByIdSql(){
        return new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id = #{id}");
        }}.toString();
    }
    public String buildUpdateByIdSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("account_name = #{u.accountName}");
            SET("profile = #{u.profile}");
            SET("password = #{u.password}");
            SET("phone_number = #{u.phoneNumber}");
            SET("transfer_limit = #{u.transferLimit}");
            SET("account_type = #{u.accountType}");
            WHERE("id =  #{id}");
        }}.toString();
    }
    public String buildSearchAccountByName(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("account_name iLIKE '%' || #{n.name} || '%'");
        }}.toString();
    }
}
