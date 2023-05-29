package com.example.api.mbanking.exception;

import com.example.api.mbanking.base.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class SecurityException {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public BaseError<?> handler(AuthenticationException e){
        return BaseError.builder()
                .status(false)
                .code(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now())
                .message("Login failed.")
                .error(e.getMessage())
                .build();
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public BaseError<?> handleEmailIsExisted(InternalAuthenticationServiceException e){
        return BaseError.builder()
                .status(false)
                .code(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now())
                .message("Login failed.")
                .error(e.getMessage())
                .build();
    }
}
