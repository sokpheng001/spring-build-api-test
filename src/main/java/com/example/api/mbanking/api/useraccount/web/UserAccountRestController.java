package com.example.api.mbanking.api.useraccount.web;

import com.example.api.mbanking.api.useraccount.UserAccountService;
import com.example.api.mbanking.base.BaseRest;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/user-accounts")
@RequiredArgsConstructor
public class UserAccountRestController {
    private final UserAccountService userAccountService;
    @GetMapping
    public BaseRest<?> selectAll(
            @RequestParam(required = false,name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "20", required = false) int limit){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User account has been selected successfully")
                .timestamp(LocalDateTime.now())
                .data(userAccountService.selectAll(page, limit))
                .build();
    }
    @PostMapping("/added")
    public BaseRest<?> createUserAccount(@RequestBody CreateUserAccountDto createUserAccountDto){
        System.out.println(createUserAccountDto.createdAt());
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User account has been created successfully")
                .timestamp(LocalDateTime.now())
                .data(userAccountService.createUserAccount(createUserAccountDto))
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> selectUserAccountById(@PathVariable("id") Integer id){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message(String.format("User account has been selected by id %d successfully",id))
                .timestamp(LocalDateTime.now())
                .data(userAccountService.selectUserAccountById(id))
                .build();
    }
    @PutMapping ("/{id}/id-updated")
    public BaseRest<?> updateAccountUserById(@PathVariable("id") Integer id, @RequestBody UpdateUserAccountIsDisabledDto updateUserAccountIsDisabledDto){
        System.out.println(updateUserAccountIsDisabledDto.isDisabled());
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message(String.format("User account has been updated by id %d successfully",id))
                .timestamp(LocalDateTime.now())
                .data(userAccountService.updateUserAccountById(id, updateUserAccountIsDisabledDto))
                .build();
    }
    @DeleteMapping("/{id}/id-deleted")
    public BaseRest<?> deleteUserAccountById(@PathVariable("id") Integer id){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message(String.format("User account has been deleted by id %d successfully",id))
                .timestamp(LocalDateTime.now())
                .data(userAccountService.deleteUserAccountById(id))
                .build();
    }
}
