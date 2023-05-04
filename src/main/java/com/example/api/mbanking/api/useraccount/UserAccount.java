package com.example.api.mbanking.api.useraccount;

import com.example.api.mbanking.api.account.Account;
import com.example.api.mbanking.api.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    private Integer id;
    private User userId;
    private Account accountId;
    private Date createdAt;
    private Boolean isDisabled;
}
