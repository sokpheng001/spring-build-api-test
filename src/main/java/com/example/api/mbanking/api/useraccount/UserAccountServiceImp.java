package com.example.api.mbanking.api.useraccount;

import com.example.api.mbanking.api.user.User;
import com.example.api.mbanking.api.useraccount.web.UserAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserAccountServiceImp implements UserAccountService {
    private final UserAccountMapper userAccountMapper;
    private final UserAccountMapStruct userAccountMapStruct;
    @Override
    public List<UserAccountDto> selectAll() {
        System.out.println(userAccountMapper.select());
        return userAccountMapStruct.fromUserAccountDtoListToUserAccountList(userAccountMapper.select());
    }
}
