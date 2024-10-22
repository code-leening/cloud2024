package com.atguigu.cloud.controller;

import com.atguigu.cloud.service.FlowLimitService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leening
 * @create 2024-08-27-10:16
 */
@RestController
@Slf4j
public class FlowLimitController
{
    @GetMapping("/testA")
    public String testA(){
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "------testB";
    }

    @Resource
    FlowLimitService flowLimitService;

    /**
     * 流控-链路
     * C和D两个请求都访问flowLimitService.common()方法，阈值达到后对C限流，对D不管
     * @return
     */
    @GetMapping("/testC")
    public String testC(){
        flowLimitService.common();
        return "------testC";
    }

    @GetMapping("/testD")
    public String testD(){
        flowLimitService.common();
        return "------testD";
    }

}
