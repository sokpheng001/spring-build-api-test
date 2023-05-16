package com.example.api.mbanking.api.auth;

import com.example.api.mbanking.api.auth.web.RegisterDto;
import com.example.api.mbanking.api.user.User;
import com.example.api.mbanking.api.user.UserMapStruct;
import com.example.api.mbanking.security.SecurityBean;
import com.example.api.mbanking.util.MailUtil;
import jakarta.mail.MessagingException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLDataException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceIpm implements AuthService{
    private final AuthMapper authMapper;
    private final UserMapStruct userMapStruct;
    private final SecurityBean securityBean;
    private final MailUtil mailUtil;
    @Value("${spring.mail.username}")
    private String appMail;
    @Transactional
    @Override
    public void register(RegisterDto registerDto) throws SQLDataException {
        if(!registerDto.password().equals(registerDto.confirmedPassword())){
            throw new ValidationException("Confirmed password is wrong!");
        }
        User user = userMapStruct.fromRegisterDtoToUser(registerDto);
        user.setPassword(securityBean.encoder().encode(registerDto.password()));
        user.setIsVerified(false);
        log.info("User: {}",user);
        if(authMapper.checkEmailIsExisted(registerDto.email())){
            throw new SQLDataException("Email is existed");
        }
        if(authMapper.register(user)){
            for (Integer role: registerDto.roleIds()){
                authMapper.createUserRole(user.getId(),role);
            }
        }
    }
    @Override
    public void verify(String email) {
        User user = authMapper.selectByEmail(email).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email is not found."));
        user.setVerifiedCode(UUID.randomUUID().toString());
        String verifiedCode = user.getVerifiedCode();
        System.out.println("VerifiedCode: " + verifiedCode);
        MailUtil.Meta<?> meta = MailUtil.Meta.builder()
                .to(email)
                .from(appMail)
                .subject("Account Verify")
                .templateUrl("auth/verify")
                .data(user)
                .build();
        try {
            mailUtil.sendMail(meta);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Mail has been failed to send");
        }
    }
    @Override
    public boolean checkVerifiedCode(String verifiedCode,String email) {
        authMapper.updateIsVerified(verifiedCode,email);
        return authMapper.checkByVerifiedCode(verifiedCode,email);
    }
}
