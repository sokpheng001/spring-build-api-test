package com.example.api.mbanking.api.useraccount;

import com.example.api.mbanking.api.useraccount.web.CreateUserAccountDto;
import com.example.api.mbanking.api.useraccount.web.UpdateUserAccountIsDisabledDto;
import com.example.api.mbanking.api.useraccount.web.UserAccountDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class UserAccountServiceImp implements UserAccountService {
    private final UserAccountMapper userAccountMapper;
    private final UserAccountMapStruct userAccountMapStruct;
    @Override
    public PageInfo<UserAccountDto> selectAll(int page, int limit) {
        PageInfo<UserAccount> userAccountPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(userAccountMapper::selectAll);
        return userAccountMapStruct.fromPageUserAccountToPageUserAccountDto(userAccountPageInfo);
    }
    @Override
    public UserAccountDto createUserAccount(CreateUserAccountDto createUserAccountDto) {
        userAccountMapper.insertUserAccount(createUserAccountDto);
        UserAccount userAccount = userAccountMapStruct.fromCreateUserAccountDtoToUserAccount(createUserAccountDto);
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
