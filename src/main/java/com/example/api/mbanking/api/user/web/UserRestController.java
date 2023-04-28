package com.example.api.mbanking.api.user.web;

import com.example.api.mbanking.api.user.UserService;
import com.example.api.mbanking.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    @PostMapping
    public BaseRest<?> createNewUser(@RequestBody @Valid CreateUserDto createUserDto){
        System.out.println(createUserDto);
        UserDto userDto = userService.createNewUser(createUserDto);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been created successfully")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> findUserById(@PathVariable Integer id){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been found successfully")
                .timestamp(LocalDateTime.now())
                .data(userService.findUserById(id))
                .build();
    }
    @GetMapping
    public BaseRest<?> findAllUser(@RequestParam(required = false,name = "page", defaultValue = "1") int page,
                                   @RequestParam(name = "limit", defaultValue = "20", required = false) int limit){
        PageInfo<UserDto> pageInfo = userService.selectAllUser(page, limit);
        System.out.println("This is : " + pageInfo);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("All users has been deleted successfully")
                .timestamp(LocalDateTime.now())
                .data(pageInfo)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseRest<?> deleteUserById(@PathVariable Integer id){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been deleted successfully")
                .timestamp(LocalDateTime.now())
                .data(userService.deleteUserById(id))
                .build();
    }
    @PutMapping ("/{id}/is-deleted")
    public BaseRest<?> UpdateIsDeletedStatusById(@PathVariable Integer id, @RequestBody IsDeletedDto isDeletedDto){
        Integer deletedId = userService.UpdateIsDeletedStatusById(id, isDeletedDto.status());
        System.out.println(isDeletedDto.status());
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Updated successfully.")
                .timestamp(LocalDateTime.now())
                .data(deletedId)
                .build();
    }
    @PutMapping("/{id}")
    public BaseRest<?> updateUserById(@PathVariable("id") Integer id, @RequestBody UpdateUserDto updateUserDto){
        UserDto userDto = userService.updateUserById(id,updateUserDto);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Updated successfully.")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }
}
