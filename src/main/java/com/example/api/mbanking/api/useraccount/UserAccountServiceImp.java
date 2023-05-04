package com.example.api.mbanking.api.useraccount;

import com.example.api.mbanking.api.user.User;
import com.example.api.mbanking.api.useraccount.web.CreateUserAccountDto;
import com.example.api.mbanking.api.useraccount.web.UpdateUserAccountIsDisabledDto;
import com.example.api.mbanking.api.useraccount.web.UserAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserAccountServiceImp implements UserAccountService {
    private final UserAccountMapper userAccountMapper;
    private final UserAccountMapStruct userAccountMapStruct;
    @Override
    public List<UserAccountDto> selectAll() {
        System.out.println("Date: " + userAccountMapper.select().get(0).getCreatedAt());
        return userAccountMapStruct.fromUserAccountDtoListToUserAccountList(userAccountMapper.select());
    }
    @Override
    public UserAccountDto createUserAccount(CreateUserAccountDto createUserAccountDto) {
        UserAccount userAccount = userAccountMapStruct.fromCreateUserAccountDtoToUserAccount(createUserAccountDto);
        userAccountMapper.insertUserAccount(createUserAccountDto);
        return userAccountMapStruct.fromUserAccountToUserAccountDto(userAccount);
    }
    @Override
    public UserAccountDto selectUserAccountById(Integer id) {
        if(userAccountMapper.isEExistedById(id)){
            return userAccountMapStruct
                    .fromUserAccountToUserAccountDto(userAccountMapper.selectUserAccountById(id));
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,String.format("User account with id %d is not found.",id));
    }
    @Override
    public Integer deleteUserAccountById(Integer id) {
        if(userAccountMapper.isEExistedById(id)){
            userAccountMapper.deleteUserAccountById(id);
            return id;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,String.format("User account with id %d is not found.",id));
    }

    @Override
    public Integer updateUserAccountById(Integer id, UpdateUserAccountIsDisabledDto updateUserAccountIsDisabledDto) {
        if(userAccountMapper.isEExistedById(id)){
            userAccountMapper.updateUserAccountIsDisabledById(id, updateUserAccountIsDisabledDto);
            return id;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,String.format("User account with id %d is not found.",id));
    }
}
