package com.example.api.mbanking.api.useraccount;

import org.apache.ibatis.jdbc.SQL;

public class UserAccountProvider {
    private final String tableName = "user_accounts";
    public String buildSelectAllSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
        }}.toString();
    }
}
