package com.example.api.mbanking.api.accounttype;
import org.apache.ibatis.jdbc.SQL;

public class AccountTypeProvider {
    public String buildSelectSql(){
        return new SQL(){{
            //TODO:
            SELECT("*");
            FROM("account_types");
        }}.toString();
    }
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO("account_types");
            VALUES("id","5");
            VALUES("name","Promoted");
        }}.toString();
    }
}
