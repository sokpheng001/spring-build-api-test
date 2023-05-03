package com.example.api.mbanking.api.account;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.dao.support.PersistenceExceptionTranslationInterceptor;

public class AccountProvider {
    private final String tableName = "accounts";
    private final String getTableName = "account_types";
//    public String buildSelectAllSql(){
//        return new SQL(){{
//            SELECT("*");
//            FROM(tableName);
//        }}.toString();
//    }
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
            VALUES("account_type","#{a.accountTypes.get(0).getId()}");
        }}.toString();
    }
}
