package com.example.api.mbanking.api.user;

import com.example.api.mbanking.api.user.web.CreateUserDto;
import com.example.api.mbanking.api.user.web.UserDto;

public interface UserService {
    UserDto createNewUser(CreateUserDto createUserDto);
    Integer deleteUserById(Integer id);
    UserDto findUserById(Integer id);
    Integer UpdateIsDeletedStatusById(Integer id,boolean status);
}
