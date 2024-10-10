package com.atguigu.cloud.apis;

import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author leening
 * @create 2024-09-06-10:49
 */
@FeignClient(value = "nacos-payment-provider",fallback = PayFeignSentinelApiFallBack.class) //微服务的名字
public interface PayFeignSentinelApi
{
    @GetMapping("/pay/nacos/get/{orderNo}")
    public ResultData<Object> getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}
