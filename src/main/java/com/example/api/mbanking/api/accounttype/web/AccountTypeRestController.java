package com.example.api.mbanking.api.accounttype.web;

        import com.example.api.mbanking.api.accounttype.AccountType;
        import com.example.api.mbanking.api.accounttype.AccountTypeService;
        import com.example.api.mbanking.base.BaseRest;

        import lombok.RequiredArgsConstructor;
        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.*;

        import java.time.LocalDateTime;
        import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-types")
public class AccountTypeRestController {
    private final AccountTypeService accountTypeService;
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
    @PostMapping("/add")
    BaseRest<?> insert(@RequestBody AccountTypeDto accountTypeDto){
        System.out.println(accountTypeDto + " It's now");
        List<AccountTypeDto> accountTypeDtos = accountTypeService.insert(accountTypeDto);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account types has been found")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDtos)
                .build();
    }
    @PostMapping("/delete")
    BaseRest<?> insert(@RequestBody AccountTypeIdDto accountTypeIdDto){
        System.out.println(accountTypeIdDto + " It's now");
        List<AccountTypeDto> accountTypeDtos = accountTypeService.delete(accountTypeIdDto.id());
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account types has been found")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDtos)
                .build();
    }

}
