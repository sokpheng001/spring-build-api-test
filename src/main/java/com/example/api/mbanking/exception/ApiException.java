package com.example.api.mbanking.exception;

import com.example.api.mbanking.base.BaseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ApiException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseError<?> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException){
        List<Map<String,String>> errors = new ArrayList<>();
        for(FieldError error: methodArgumentNotValidException.getFieldErrors()){
            Map<String, String> errorDetail = new HashMap<>();
            errorDetail.put("name",error.getField());
            errorDetail.put("message : ",error.getDefaultMessage());
            errors.add(errorDetail);
        }
        return BaseError.builder()
                .status(false)
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message("Error")
                .error(errors)
                .build();
    }
}