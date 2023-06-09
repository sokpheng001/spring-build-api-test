package com.example.api.mbanking.api.user;

import com.example.api.mbanking.api.user.web.*;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceIpm implements UserService{
    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;
    private final UserIdNotFoundServiceHandler userIdNotFoundServiceHandler;
    @Override
    public UserDto createNewUser(CreateUserDto createUserDto) {
        User user = userMapStruct.createUserDtoToUser(createUserDto);
        userMapper.insert(user);
        log.info("oneSignalId = {}",user.getId());
        return this.findUserById(user.getId());
    }
    @Override
    public PageInfo<UserDto> selectAllUser(int page, int limit) {
        PageInfo<User> userPageInfo= PageHelper.startPage(page, limit).doSelectPageInfo(userMapper::select);
        return userMapStruct.userPageInfoToUserDtoPageInfo(userPageInfo);
    }
    @Override
    public Integer deleteUserById(Integer id) {
        boolean isFound = userMapper.existById(id);
        if(isFound){
            userMapper.deleteById(id);
        }else {
            userIdNotFoundServiceHandler.HandlerId(id);
        }
        return id;
    }
    @Override
    public UserDto findUserById(Integer id) {
        User user = userMapper.selectById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %d is not found",id)));
        return userMapStruct.userToUserDto(user);
    }
    @Override
    public Integer updateIsDeletedStatusById(Integer id,boolean status) {
        if(userMapper.existById(id)){
            userMapper.updateIsDeletedById(id, status);
        }else {
            userIdNotFoundServiceHandler.HandlerId(id);
        }
        return id;
    }
    @Override
    public UserDto updateUserById(Integer id, UpdateUserDto updateUserDto) {
        if(userMapper.existById(id)){
            User user = userMapStruct.updateUserDtotoUser(updateUserDto);
            user.setId(id);
            userMapper.updateById(user);
            return this.findUserById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with id %d is not found",id));
    }
    @Override
    public List<UserDto> searchUserByName(UserDtoSearchByName searchByName){
        if(userMapper.existByUserName(searchByName.name())){
            return userMapStruct.fromUserListToUserDtoList(userMapper.searchUserByName(searchByName));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("User with name %s is not found",searchByName.name()));
        }
    }
    @Override
    public List<UserDto> searchUserByStudentCardId(StudentCardIDDto studentCardIDDto) {
        if(userMapper.existByStudentCardId(studentCardIDDto.cardId())){
            List<User> users = userMapper.searchUserByStudentCardId(studentCardIDDto);
            return userMapStruct.fromUserListToUserDtoList(users);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("User with card id %s is not found",studentCardIDDto.cardId()));
        }
    }
}
