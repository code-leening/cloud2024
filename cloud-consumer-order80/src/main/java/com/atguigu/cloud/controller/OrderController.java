package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author leening
 * @create 2024-08-01-15:14
 */
@RestController
public class OrderController {

    //public static final String PAYMENT_URL = "http://localhost:8001";//先写死，硬编码
    public static final String PAYMENT_URL = "http://cloud-payment-service";//服务注册中心上的微服务名称

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "consumer/pay/add")
    public Object addOrder(PayDTO payDTO)
    {
         return restTemplate.postForObject(PAYMENT_URL + "/pay/add", payDTO, ResultData.class);
    }

    @GetMapping(value = "/consumer/pay/get/{id}")
    public Object getPayInfo(@PathVariable("id") Integer id)
    {
        return restTemplate.getForObject(PAYMENT_URL + "/pay/get/" + id, ResultData.class, id);
    }

    @GetMapping(value = "/consumer/pay/getAll")
    public Object getPayList()
    {
        return restTemplate.getForObject(PAYMENT_URL + "/pay/getAll", ResultData.class);
    }

    @GetMapping(value = "/consumer/pay/get/info")
    public String getInfoByConsul(){
        return restTemplate.getForObject(PAYMENT_URL + "/pay/get/info", String.class);
    }
}
