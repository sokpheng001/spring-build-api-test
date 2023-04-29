package com.example.api.mbanking.api.accounttype;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class AccountTypeProvider {
    private final String tableName = "account_types";
    public String buildSelectSql(){
        return new SQL(){{
            //TODO:
            SELECT("*");
            FROM(tableName);
        }}.toString();
    }
    public String buildInsertSql() {
        return new SQL() {{
            INSERT_INTO(tableName);
            VALUES("name", "#{n.name}");
        }}.toString();
    }
        public String buildSelectByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id  = #{id}");
        }}.toString();
    }
    public String buildUpdateByIdSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("name = #{accountType.name}");
            WHERE("id  = #{id}");
        }}.toString();
    }
    public String buildDeleteByIdSql(){
        return new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id = #{id}");
        }}.toString();
    }
}
