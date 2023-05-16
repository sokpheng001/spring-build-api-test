package com.example.api.mbanking.exception;

import com.example.api.mbanking.base.BaseError;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
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
    @ExceptionHandler({ResponseStatusException.class})
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
    @ExceptionHandler(DataIntegrityViolationException.class)
    public BaseError<?> handlerForDeletingForeignKey(DataIntegrityViolationException e){
        return BaseError.builder()
                .status(false)
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .message("Cannot delete account by this id, cuz this id is a foreign ky of another table.")
                .error("Cannot delete account.")
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

    /**
     *
     * @param e used for catching RuntimeException
     * @return BaseError<?>
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RuntimeException.class)
    public BaseError<?> handleNoFileForDownload(RuntimeException e){
        return BaseError.builder()
                .status(false)
                .code(HttpStatus.NOT_FOUND.ordinal())
                .timestamp(LocalDateTime.now())
                .message("File is not exited.")
                .error(e.getMessage())
                .build();
    }

    /**
     *
     * @param nullPointerException used for catching nullPointerException
     * @return BaseError<?>
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NullPointerException.class)
    public BaseError<?> handleMultipart(NullPointerException nullPointerException){
        return BaseError.builder()
                .status(false)
                .code(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .message("File is not exited.")
                .error("File is not found.")
                .build();
    }

    /**
     * Used for checking confirmed password.
     * @param e
     * @return BaseError<?>
     */
    @ResponseStatus(HttpStatus.IM_USED)
    @ExceptionHandler(ValidationException.class)
    public BaseError<?> handlePasswordInsertion(ValidationException e){
        return BaseError.builder()
                .status(false)
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .message("Invalid password")
                .error(e.getMessage())
                .build();
    }

    /**
     * Used for checking email is existed.
     * @param e
     * @return BaseError<?>
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SQLException.class)
    public BaseError<?> handleEmailIsExisted(SQLException e){
        return BaseError.builder()
                .status(false)
                .code(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now())
                .message("Email is existed.")
                .error(e.getMessage())
                .build();
    }
}
