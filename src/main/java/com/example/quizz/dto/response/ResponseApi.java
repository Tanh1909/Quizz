package com.example.quizz.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseApi {
    private int code;
    private String message;
    private Object data;

    public static ResponseApi success(Object data){
        return new ResponseApi(200,"SUCCESS",data);
    }
    public static ResponseApi error(int code,String message){
        return new ResponseApi(code,message,null);
    }
}
