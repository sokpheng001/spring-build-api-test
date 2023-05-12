package com.example.api.mbanking.api.auth;

import com.example.api.mbanking.api.auth.web.RegisterDto;
import com.example.api.mbanking.api.user.User;
import com.example.api.mbanking.api.user.UserMapStruct;
import com.example.api.mbanking.security.SecurityBean;
import com.example.api.mbanking.util.MailUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    @Override
    public void register(RegisterDto registerDto) {
        User user = userMapStruct.fromRegisterDtoToUser(registerDto);
        user.setPassword(securityBean.encoder().encode(registerDto.password()));
        log.info("User: {}",user);
        authMapper.register(user);
    }
    @Override
    public void verify(String email) {
        User user = authMapper.selectByEmail(email);
        System.out.println(email);
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
}
