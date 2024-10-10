package com.atguigu.cloud.handler;

import com.atguigu.cloud.resp.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author leening
 * @create 2024-08-01-10:22
 */
@Slf4j //输出、日志
@RestControllerAdvice //拦截器
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e)
    {
        e.printStackTrace();
        log.error("全局异常信息：{}",e.getMessage(),e);
        return ResultData.fail(e.getMessage());
    }
}
