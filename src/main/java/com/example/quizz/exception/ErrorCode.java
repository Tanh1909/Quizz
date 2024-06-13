package com.example.quizz.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,1000,"SOMETHING IS WRONG!"),
    USERNAME_ALREADY_EXISTS(HttpStatus.CONFLICT,1001,"USERNAME ALREADY EXISTS!"),
    TOPIC_NOT_FOUND(HttpStatus.NOT_FOUND,1002,"TOPIC NOT FOUND!"),
    TOPIC_IS_EMPTY(HttpStatus.NO_CONTENT,1003,"TOPIC IS EMPTY"),
    QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND,1004,"QUESTION NOT FOUND!"),
    QUESTION_IS_EMPTY(HttpStatus.NO_CONTENT,1005,"QUESTION IS EMPTY"),
    ANSWERS_ARRAY_EMPTY(HttpStatus.BAD_REQUEST,1006,"ANSWERS ARRAY CANNOT BE EMPTY!"),
    QUESTIONS_ARRAY_EMPTY(HttpStatus.BAD_REQUEST,1007,"QUESTION ARRAY CANNOT BE EMPTY!")
    ;
    private HttpStatus httpStatus;
    private int code;
    private String message;

}
