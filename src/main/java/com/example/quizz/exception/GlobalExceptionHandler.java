package com.example.quizz.exception;

import com.example.quizz.dto.response.ResponseApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception e){
        ErrorCode errorCode=ErrorCode.INTERNAL_SERVER_ERROR;
        log.error(e.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(ResponseApi.error(errorCode.getCode(), errorCode.getMessage()));
    }
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> exception(AppException e){
        ErrorCode errorCode=e.getErrorCode();
        log.error(e.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(ResponseApi.error(errorCode.getCode(), errorCode.getMessage()));
    }
}
