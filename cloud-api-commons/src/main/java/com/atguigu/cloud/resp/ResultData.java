package com.atguigu.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author leening
 * @create 2024-08-01-09:25
 */
@Data
@Accessors(chain = true)//链式编程
public class ResultData<T>
{
    /**
     * 结果状态，具体状态码参见枚举值类ResultCodeEnum.java
     */
    private Integer code;
    private String message;
    private T data;
    private long timestamp;

    public ResultData(){
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data){
        return custom(ReturnCodeEnum.RC200.getCode(), ReturnCodeEnum.RC200.getMessage(),data);
    }

    public static <T> ResultData<T> fail(Integer code, String message){
        return custom(code,message,null);
    }

    public static <T> ResultData<T> fail(){
        return custom(ReturnCodeEnum.RC999.getCode(), ReturnCodeEnum.RC999.getMessage(), null);
    }
    public static <T> ResultData<T> fail(String message){
        return custom(999, message, null);
    }

    public static <T> ResultData<T> custom(Integer code, String massage, T data) {
        ResultData<T> result = new ResultData<>();
        result.setCode(code);
        result.setMessage(massage);
        result.setData(data);
        return result;
    }



}
