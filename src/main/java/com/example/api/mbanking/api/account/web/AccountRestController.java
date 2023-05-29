package com.example.api.mbanking.api.account.web;


import com.example.api.mbanking.api.account.AccountService;
import com.example.api.mbanking.base.BaseRest;
import com.example.api.mbanking.security.SecurityConfig;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountRestController {
    private final AccountService accountService;
    @PostMapping("/added")
    public BaseRest<?> createAccount(@RequestBody @Valid CreateAccountDto createAccountDto){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account has been created successfully")
                .timestamp(LocalDateTime.now())
                .data(accountService.createAccount(createAccountDto))
                .build();
    }
    @GetMapping
    public BaseRest<?> selectAllAccount(
            @RequestParam(required = false,name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "20", required = false) int limit){
        PageInfo<AccountDto> pageInfo = accountService.selectAll(page,limit);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Accounts have been selected successfully")
                .timestamp(LocalDateTime.now())
                .data(pageInfo)
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> selectById(@PathVariable("id") Integer id){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account has been selected successfully")
                .timestamp(LocalDateTime.now())
                .data(accountService.searchAccountById(id))
                .build();
    }
    @PatchMapping ("/{id}/id-updated")
    public BaseRest<?> updateAccountById(@PathVariable Integer id, @RequestBody UpdateAccountDto updateAccountDto){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account has been updated successfully")
                .timestamp(LocalDateTime.now())
                .data(accountService.updateAccountById(id, updateAccountDto))
                .build();
    }
    @DeleteMapping("/{id}/id-deleted")
    public BaseRest<?> deleteAccountById(@PathVariable("id") Integer id){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account has been deleted successfully")
                .timestamp(LocalDateTime.now())
                .data(accountService.deleteAccountById(id))
                .build();
    }
    @GetMapping("/name-searched")
    public BaseRest<?> searchAccountByName(@RequestBody SearchAccountByNameDto searchAccountByNameDto){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account has been found successfully")
                .timestamp(LocalDateTime.now())
                .data(accountService.searchByAccountName(searchAccountByNameDto))
                .build();
    }
}

