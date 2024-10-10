package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author leening
 * @create 2024-08-08-10:48
 */
@RestController
public class PayCircuitController
{
    //circuitBreaker 溶断例子
    @GetMapping("/pay/circuit/{id}")
    public ResultData<String> myCircuit(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("Id不能为负数 ... ");
        }
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResultData.success("Hello , circuit ! inputId:" + id + " UUID:" + IdUtil.simpleUUID());
    }


    //Resilience4j bulkhead例子
    @GetMapping("/pay/bulkhead/{id}")
    public ResultData<String> mySemaphore(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("Id不能为负数 ... ");
        }
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ResultData.success("Hello Resilience4j bulkhead！inputId:" + id + " UUID:" + IdUtil.simpleUUID());
    }

    @GetMapping(value = "/pay/ratelimit/{id}")
    public ResultData<String> myRateLimit(@PathVariable("id") Integer id) {
        return ResultData.success("Hello RateLimit inputId:" + id + " UUID:" + IdUtil.simpleUUID());
    }
}
