package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Result <T>{
    private Integer code;//狀態 0-成功 1-失敗
    private String message;
    private T data;//數據

    public static <E> Result<E> success(E data){
        return  new Result<>(0 , "成功" , data);
    }

    public static Result success(){
        return  new Result<>(0 , "成功" , null);
    }

    public static Result error(String message){
        return  new Result(1 , message , null);
    }
}
