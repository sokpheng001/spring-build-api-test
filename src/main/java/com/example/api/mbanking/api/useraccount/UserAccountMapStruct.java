package com.example.api.mbanking.api.useraccount;

import com.example.api.mbanking.api.useraccount.web.CreateUserAccountDto;
import com.example.api.mbanking.api.useraccount.web.UserAccountDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAccountMapStruct {
    List<UserAccountDto> fromUserAccountDtoListToUserAccountList(List<UserAccount> userAccounts);
    UserAccount fromCreateUserAccountDtoToUserAccount(CreateUserAccountDto createUserAccountDto);
    UserAccountDto fromUserAccountToUserAccountDto(UserAccount userAccount);
}
