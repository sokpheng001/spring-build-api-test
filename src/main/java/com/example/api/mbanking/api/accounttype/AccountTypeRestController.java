package com.example.api.mbanking.api.accounttype;

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
    @GetMapping("/add")
    BaseRest<?> insert(@RequestBody AccountType accountType){
        accountTypeService.insert();
        System.out.println(accountType);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account types has been found")
                .timestamp(LocalDateTime.now())
                .data(accountType)
                .build();
    }
}
