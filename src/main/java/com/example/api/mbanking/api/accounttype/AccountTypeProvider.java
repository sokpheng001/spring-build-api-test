package com.example.api.mbanking.api.accounttype;
import org.apache.ibatis.jdbc.SQL;

public class AccountTypeProvider {
    private final String tableName = "account_types";
    public String buildSelectSql(){
        return new SQL(){{
            //TODO:
            SELECT("*");
            FROM(tableName);
        }}.toString();
    }
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO("account_types");
                    VALUES("id","#{i.id}");
                    VALUES("name","#{i.name}");
        }}.toString();
    }
}
