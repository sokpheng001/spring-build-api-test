package com.example.api.mbanking.api.accounttype.web;

        import com.example.api.mbanking.api.accounttype.AccountTypeService;
        import com.example.api.mbanking.api.user.web.CreateUserDto;
        import com.example.api.mbanking.base.BaseRest;
        import lombok.RequiredArgsConstructor;
        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.*;
        import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-types")
public class AccountTypeRestController {
    private final AccountTypeService accountTypeService;
    @PostMapping("/added-account")
    public BaseRest<?> createAccountType(@RequestBody CreateUserDto createUserDto){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account types has been found")
                .timestamp(LocalDateTime.now())
                .data(accountTypeService.insert(createUserDto))
                .build();
    }
    @GetMapping
    BaseRest<?> findAll(){
        var accountTypDtoList = accountTypeService.findAll();
        System.out.println("You Got It !!!");
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account types has been found")
                .timestamp(LocalDateTime.now())
                .data(accountTypDtoList)
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> selectById(@PathVariable Integer id){
        System.out.println(accountTypeService.selectById(id));
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account type has been selected successfully.")
                .timestamp(LocalDateTime.now())
                .data(accountTypeService.selectById(id))
                .build();
    }
    @PutMapping("/{id}/id-updated")
    public BaseRest<?> updateAccountTypeById(@PathVariable("id") Integer id, @RequestBody AccountTypeDto accountTypeDto){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account type has been updated successfully.")
                .timestamp(LocalDateTime.now())
                .data(accountTypeService.updatedById(id, accountTypeDto))
                .build();
    }
    @DeleteMapping("/{id}/id-deleted")
    public BaseRest<?> deleteAccountTypeById(@PathVariable("id") Integer id){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account type has been deleted successfully.")
                .timestamp(LocalDateTime.now())
                .data(accountTypeService.serviceDeleteAccountTypeById(id))
                .build();
    }

}

