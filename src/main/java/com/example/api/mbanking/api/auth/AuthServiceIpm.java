package com.example.api.mbanking.api.auth;

import com.example.api.mbanking.api.auth.web.AuthDto;
import com.example.api.mbanking.api.auth.web.LoginDto;
import com.example.api.mbanking.api.auth.web.RegisterDto;
import com.example.api.mbanking.api.user.User;
import com.example.api.mbanking.api.user.UserMapStruct;
import com.example.api.mbanking.security.SecurityBean;
import com.example.api.mbanking.util.MailUtil;
import jakarta.mail.MessagingException;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLDataException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceIpm implements AuthService{
    private final AuthMapper authMapper;
    private final UserMapStruct userMapStruct;
    private final SecurityBean securityBean;
    private final MailUtil mailUtil;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtEncoder jwtEncoder;
    @Value("${spring.mail.username}")
    private String appMail;
    @Transactional// used for prevent if-can not insert into the table in-data-base
    @Override
    public void register(RegisterDto registerDto) throws SQLDataException {
        if(!registerDto.password().equals(registerDto.confirmedPassword())){
            throw new ValidationException("Confirmed password is wrong!");
        }
        User user = userMapStruct.fromRegisterDtoToUser(registerDto);
        user.setPassword(securityBean.encoder().encode(registerDto.password()));
        user.setIsVerified(false);
        log.info("User: {}",user);
//        if(authMapper.checkEmailIsExisted(registerDto.email())){
//            throw new SQLDataException("Email is existed");
//        }
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
    @Override
    public AuthDto login(LoginDto loginDto){
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.email(),loginDto.password());
        authentication = daoAuthenticationProvider.authenticate(authentication);
        Instant instant = Instant.now();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(data->!data.startsWith("ROLE_"))
                .collect(Collectors.joining(" "));
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(instant)
                .subject(authentication.getName())
                .expiresAt(Instant.now().plus(1,ChronoUnit.HOURS))
                .claim("scope",scope)
                .build();
        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        return new AuthDto("Bearer",accessToken);
        //define scope
        //        String format = authentication.getName() + ":" + authentication.getCredentials();
//        String endCoding = Base64.getEncoder().encodeToString(format.getBytes());
//        System.out.println("Password: " + endCoding);
//        String scope = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
//        return new AuthDto(String.format("Basic %s",endCoding));
    }
}
