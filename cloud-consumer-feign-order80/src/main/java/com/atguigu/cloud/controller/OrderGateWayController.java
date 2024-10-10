package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leening
 * @create 2024-08-13-10:11
 */
@RestController
@RequestMapping("/feign")
public class OrderGateWayController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/pay/gateway/get/{id}")
    public Object getPayGatewayById(@PathVariable("id") Integer id) {
        return payFeignApi.getGatewayById(id);
    }

    @GetMapping("/pay/gateway/info")
    public Object getPayGatewayInfo() {
        return payFeignApi.getGatewayInfo();
    }
}
