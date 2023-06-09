package com.example.api.mbanking.api.accounttype;

import com.example.api.mbanking.api.accounttype.web.AccountTypeDto;
import com.example.api.mbanking.api.accounttype.web.CreateAccountTypeDto;
import com.example.api.mbanking.api.user.web.CreateUserDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImp implements AccountTypeService{
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeMapStruct;
    @Override
    public PageInfo<AccountTypeDto> findAll(int page, int limit) {
        List<AccountType> accountTypes = accountTypeMapper.select();
//        List<AccountTypeDto> accountTypeDtos = accountTypes
//                .stream()
//                .map(accountType -> new AccountTypeDto(accountType.getName())).collect(Collectors.toList());
        //        return accountTypeDtos;
        PageInfo<AccountType> accountTypePageInfo = PageHelper.startPage(page,limit).doSelectPageInfo(accountTypeMapper::select);
        return accountTypeMapStruct.accountTypePageToAccountTypeDtoPage(accountTypePageInfo);
    }
    @Override
    public AccountTypeDto insert(CreateAccountTypeDto createAccountTypeDto) {
        AccountType accountType = accountTypeMapStruct.fromCreateAccountDtotoAccountType(createAccountTypeDto);
        accountTypeMapper.insert(accountType);
        return accountTypeMapStruct.fromAccountTypeToAccountTypeDto(accountType);
    }
    @Override
    public List<AccountTypeDto> selectById(Integer id) {
        if(accountTypeMapper.isExisted(id)) {
            List<AccountType> accountTypeList = accountTypeMapper.selectById(id);
            return accountTypeMapStruct.toDtoList(accountTypeList);
        }else {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "This account type id is not founded.");
        }
    }
    @Override
    public AccountTypeDto updatedById(Integer id, AccountTypeDto accountTypeDto) {
        if(accountTypeMapper.isExisted(id)){
            accountTypeMapper.updateAccountTypeById(id,accountTypeDto);
            return accountTypeMapStruct.fromAccountTypeToAccountTypeDto(accountTypeMapStruct.toAccountType(accountTypeDto));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Account type with id numbered %d not found",id));
        }
    }
    @Override
    public Integer serviceDeleteAccountTypeById(Integer id){
        if(accountTypeMapper.isExisted(id)){
            accountTypeMapper.deleteAccountTypesById(id);
            return id;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Account type id %d is not found.",id));
        }
    }
}
