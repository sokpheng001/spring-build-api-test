package com.example.api.mbanking.api.useraccount;

import com.example.api.mbanking.api.account.Account;
import com.example.api.mbanking.api.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    private Integer id;
    private List<User> userId;
    private List<Account> accountId;
    private Date createdAt;
    private Boolean isDisabled;
}
