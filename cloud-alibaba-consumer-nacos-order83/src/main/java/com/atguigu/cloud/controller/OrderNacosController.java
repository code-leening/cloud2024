package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignSentinelApi;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author leening
 * @create 2024-08-22-15:27
 */
@RestController
public class OrderNacosController {

    public static final String PAYMENT_URL = "http://nacos-payment-provider";//服务注册中心上的微服务名称

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/consumer/pay/nacos/{id}")
    public Object getPayInfo2(@PathVariable("id") Integer id){
        System.out.println("id = " + id);
        String result = restTemplate.getForObject( serverURL + "/pay/nacos/" + id, String.class);
        return result + "\t" + "我是OrderNacosController83调用者";
        //return restTemplate.getForObject(serverURL + "/pay/nacos/" + id, String.class,id);
    }


    // ===========
    @Resource
    private PayFeignSentinelApi payFeignSentinelApi;

    @GetMapping("/consumer/pay/nacos/get/{orderNo}")
    public Object getPayByOrderNo(@PathVariable("orderNo") String orderNo){
        return payFeignSentinelApi.getPayByOrderNo(orderNo);
    }

}
