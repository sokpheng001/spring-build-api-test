package com.example.api.mbanking.api.account;

import com.example.api.mbanking.api.accounttype.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Integer id;
    private String accountNo;
    private String accountName;
    private String profile;
    private Integer pin;
    private Integer password;
    private Integer phoneNumber;
    private Double transferLimit;
    private AccountType accountType;
}
