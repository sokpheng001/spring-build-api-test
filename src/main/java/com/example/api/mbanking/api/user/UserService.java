package com.example.api.mbanking.api.user;

import com.example.api.mbanking.api.user.web.CreateUserDto;
import com.example.api.mbanking.api.user.web.UserDto;


public interface UserService {
    UserDto createNewUser(CreateUserDto createUserDto);
    Boolean deleteUser(Integer id);
    UserDto findUserById(Integer id);
}
