package com.example.api.mbanking.api.account;

import com.example.api.mbanking.api.account.web.AccountDto;
import com.example.api.mbanking.api.account.web.CreateAccountDto;
import com.example.api.mbanking.api.account.web.SearchAccountByNameDto;
import com.example.api.mbanking.api.account.web.UpdateAccountDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountServiceIpm implements AccountService{
    private final AccountMapper accountMapper;
    private final AccountMapStruct accountMapStruct;

    @Override
    public PageInfo<AccountDto> selectAll(int page, int limit) {
        PageInfo<Account> accountPage = PageHelper.startPage(page, limit).doSelectPageInfo(accountMapper::select);
        return accountMapStruct.fromPageInfoAccountToPageInfoAccountDto(accountPage);
    }
    @Override
    public AccountDto createAccount(CreateAccountDto createAccountDto) {
        Account account = accountMapStruct.fromCreateAccountDtoToAccount(createAccountDto);
        accountMapper.insert(account);
        return accountMapStruct.fromCreateAccountDtoToAccountDto(createAccountDto);
    }

    @Override
    public AccountDto searchAccountById(Integer id) {
        if(accountMapper.isExistedById(id)){
            Account account = accountMapper.selectById(id);
            return accountMapStruct.fromAccountToAccountDto(account);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND
                ,String.format("User with id %d is not found...!",id));
    }

    @Override
    public AccountDto updateAccountById(Integer id, UpdateAccountDto updateAccountDto) {
        if(accountMapper.isExistedById(id)) {
            accountMapper.updateById(id, updateAccountDto);
            Account account = accountMapStruct.frommUpdateAccountDtoToAccount(updateAccountDto);
            account.setId(id);
            return accountMapStruct.fromAccountToAccountDto(account);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND
                ,String.format("User with id %d is not found...!",id));
    }
    @Override
    public Integer deleteAccountById(Integer id) {
        System.out.println("Result is existed or not: " + accountMapper.isExistedById(id));
        if(accountMapper.isExistedById(id)) {
            accountMapper.delete(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND
                ,String.format("User with id %d is not found...!",id));
    }
    @Override
    public List<AccountDto> searchByAccountName(SearchAccountByNameDto searchAccountByNameDto) {
        if(accountMapper.isExistedByName(searchAccountByNameDto.name())){
            return accountMapStruct
                    .fromAccountListToAccountDtoList(accountMapper.searchAccountByName(searchAccountByNameDto));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Account named %s is not found...!",searchAccountByNameDto.name()));
    }
}

