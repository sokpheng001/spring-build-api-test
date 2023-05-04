package com.example.api.mbanking.api.useraccount;

import net.sf.jsqlparser.expression.ValueListExpression;
import org.apache.ibatis.jdbc.SQL;

import javax.naming.SizeLimitExceededException;

public class UserAccountProvider {
    private final String tableName = "user_accounts";
    public String buildSelectAllSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
        }}.toString();
    }
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("user_id","#{a.userId.id}");
            VALUES("account_id","#{a.accountId.id}");
            VALUES("created_at","#{a.createdAt}");
            VALUES("is_disabled","FALSE");
        }}.toString();
    }
    public String buildSelectSqlById(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id = #{id}");
        }}.toString();
    }
    public String buildUpdateSqlById(){
        return new SQL(){{
            UPDATE(tableName);
            SET("is_disabled = #{status.isDisabled}");
            WHERE("id = #{id}");
        }}.toString();
    }
    public String buildDeleteSqlById(){
        return new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id = #{id}");
        }}.toString();
    }
}
