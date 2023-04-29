package com.example.api.mbanking.api.user;

import com.example.api.mbanking.api.user.web.CreateUserDto;
import com.example.api.mbanking.api.user.web.UpdateUserDto;
import com.example.api.mbanking.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapStruct {
    User updateUserDtotoUser(UpdateUserDto userDto);
    User createUserDtoToUser(CreateUserDto createUserDto);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    PageInfo<UserDto> userPageInfoToUserDtoPageInfo(PageInfo<User> userPageInfo);
    //search
    List<UserDto> fromUserListToUserDtoList(List<User> users);
}
