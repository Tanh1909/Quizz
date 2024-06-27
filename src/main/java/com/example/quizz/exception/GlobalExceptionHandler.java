package com.example.quizz.exception;

import com.example.quizz.dto.response.ResponseApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception e){
        ErrorCode errorCode=ErrorCode.INTERNAL_SERVER_ERROR;
        log.error(e.getClass().getName());
        log.error(e.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(ResponseApi.error(errorCode.getCode(), errorCode.getMessage()));
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> unauthorizedException(AccessDeniedException e){
        ErrorCode errorCode=ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getHttpStatus()).body(ResponseApi.error(errorCode.getCode(), errorCode.getMessage()));
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> unauthenticated(AuthenticationException e){
        ErrorCode errorCode=ErrorCode.UNAUTHENTICATED;

        return ResponseEntity.status(errorCode.getHttpStatus()).body(ResponseApi.error(errorCode.getCode(), errorCode.getMessage()));
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> exception(AppException e){
        ErrorCode errorCode=e.getErrorCode();
        log.error(e.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(ResponseApi.error(errorCode.getCode(), errorCode.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorCode e=ErrorCode.METHOD_ARGUMENT_NOT_VALID;
        return ResponseEntity.status(e.getHttpStatus()).body(ResponseApi.error(e.getCode(),errors.toString()));
    }
}
