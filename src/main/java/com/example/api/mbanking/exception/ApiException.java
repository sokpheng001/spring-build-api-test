package com.example.api.mbanking.exception;

import com.example.api.mbanking.base.BaseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ApiException {
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

    /**
     *
     * @param e used for handle respondStatusException
     * @return BaseError<?>
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResponseStatusException.class)
    public BaseError<?> handleServiceException(ResponseStatusException e){
        return BaseError.builder()
                .status(false)
                .code(e.getStatusCode().value())
                .timestamp(LocalDateTime.now())
                .message("Something went wrong...!!!, please check.")
                .error(e.getReason())
                .build();
    }

    /**
     *
     * @param methodArgumentNotValidException used for validation for fields
     * @return BaseError<?>
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseError<?> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException){
        List<Map<String,String>> errors = new ArrayList<>();
        for(FieldError error: methodArgumentNotValidException.getFieldErrors()){
            Map<String, String> errorDetail = new HashMap<>();
            errorDetail.put("field",error.getField());
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
    /**
     *
     * @param e used for validate file size for uploading
     * @return BaseError<?>
     */
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public BaseError<?> handleValidationException(MaxUploadSizeExceededException e){
        return BaseError.builder()
                .status(false)
                .code(HttpStatus.PAYLOAD_TOO_LARGE.value())
                .timestamp(LocalDateTime.now())
                .message("Please check file size again.")
                .error(e.getMessage()+": 1MB (1024KB)")
                .build();
    }
}
