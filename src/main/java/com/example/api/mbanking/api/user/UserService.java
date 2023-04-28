package com.example.api.mbanking.api.user;

import com.example.api.mbanking.api.user.web.CreateUserDto;
import com.example.api.mbanking.api.user.web.UpdateUserDto;
import com.example.api.mbanking.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;

public interface UserService {
    UserDto createNewUser(CreateUserDto createUserDto);
    PageInfo<UserDto> selectAllUser(int page, int limit);
    Integer deleteUserById(Integer id);
    UserDto findUserById(Integer id);
    Integer UpdateIsDeletedStatusById(Integer id,boolean status);
    UserDto updateUserById(Integer id, UpdateUserDto userDto);
}
