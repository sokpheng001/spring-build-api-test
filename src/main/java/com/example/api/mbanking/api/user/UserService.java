package com.example.api.mbanking.api.user;

import com.example.api.mbanking.api.user.web.*;
import com.github.pagehelper.PageInfo;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;

import java.util.List;

public interface UserService {
    UserDto createNewUser(CreateUserDto createUserDto);
    PageInfo<UserDto> selectAllUser(int page, int limit);
    Integer deleteUserById(Integer id);
    UserDto findUserById(Integer id);
    Integer updateIsDeletedStatusById(Integer id,boolean status);
    UserDto updateUserById(Integer id, UpdateUserDto userDto);
    List<UserDto> searchUserByName(UserDtoSearchByName searchByName);
    List<UserDto> searchUserByStudentCardId(StudentCardIDDto studentCardIDDto);
}
