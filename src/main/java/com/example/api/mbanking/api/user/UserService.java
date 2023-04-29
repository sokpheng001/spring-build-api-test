package com.example.api.mbanking.api.user;

import com.example.api.mbanking.api.user.web.CreateUserDto;
import com.example.api.mbanking.api.user.web.UpdateUserDto;
import com.example.api.mbanking.api.user.web.UserDto;
import com.example.api.mbanking.api.user.web.UserDtoSearchByName;
import com.github.pagehelper.PageInfo;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;

import java.util.List;

public interface UserService {
    UserDto createNewUser(CreateUserDto createUserDto);
    PageInfo<UserDto> selectAllUser(int page, int limit);
    Integer deleteUserById(Integer id);
    UserDto findUserById(Integer id);
    Integer UpdateIsDeletedStatusById(Integer id,boolean status);
    UserDto updateUserById(Integer id, UpdateUserDto userDto);
    List<UserDto> searchUserByName(UserDtoSearchByName searchByName);
    List<UserDto> searchUserByStudentCardId(String studentCardId);
}
