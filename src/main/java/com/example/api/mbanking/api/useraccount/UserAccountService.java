package com.example.api.mbanking.api.useraccount;

import com.example.api.mbanking.api.user.User;
import com.example.api.mbanking.api.useraccount.web.UserAccountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserAccountService {
    List<UserAccountDto> selectAll();
}
