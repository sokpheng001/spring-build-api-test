package com.example.api.mbanking.api.user;

import com.example.api.mbanking.api.user.web.CreateUserDto;
import com.example.api.mbanking.api.user.web.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public Integer UpdateIsDeletedStatusById(Integer id,boolean status) {
        boolean isExisted = userMapper.existById(id);
        if(isExisted){
            userMapper.updateIsDeletedById(id, status);
        }else {
            userIdNotFoundServiceHandler.HandlerId(id);
        }
        return id;
    }
}
