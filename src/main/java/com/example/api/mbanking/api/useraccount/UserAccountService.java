package com.example.api.mbanking.api.useraccount;

import com.example.api.mbanking.api.useraccount.web.CreateUserAccountDto;
import com.example.api.mbanking.api.useraccount.web.UpdateUserAccountIsDisabledDto;
import com.example.api.mbanking.api.useraccount.web.UserAccountDto;
import com.github.pagehelper.PageInfo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public interface UserAccountService {
    PageInfo<UserAccountDto> selectAll(int page, int limit);
    UserAccountDto createUserAccount(CreateUserAccountDto createUserAccountDto);
    UserAccountDto selectUserAccountById(Integer id);
    Integer updateUserAccountById(Integer id, UpdateUserAccountIsDisabledDto updateUserAccountIsDisabledDto);
    Integer deleteUserAccountById(Integer id);
}
