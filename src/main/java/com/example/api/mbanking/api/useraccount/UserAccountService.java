package com.example.api.mbanking.api.useraccount;

import com.example.api.mbanking.api.useraccount.web.CreateUserAccountDto;
import com.example.api.mbanking.api.useraccount.web.UpdateUserAccountIsDisabledDto;
import com.example.api.mbanking.api.useraccount.web.UserAccountDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public interface UserAccountService {
    List<UserAccountDto> selectAll();
    UserAccountDto createUserAccount(CreateUserAccountDto createUserAccountDto);
    UserAccountDto selectUserAccountById(Integer id);
    Integer updateUserAccountById(Integer id, UpdateUserAccountIsDisabledDto updateUserAccountIsDisabledDto);
    Integer deleteUserAccountById(Integer id);
}
