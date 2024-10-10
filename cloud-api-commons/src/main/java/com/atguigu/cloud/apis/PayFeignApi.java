package com.atguigu.cloud.apis;

import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Enumeration;

/**
 * @author leening
 * @create 2024-08-06-09:52
 */
//@FeignClient(value = "cloud-payment-service") //微服务的名字
@FeignClient(value = "cloud-gateway") //网关的名字
public interface PayFeignApi {

    @PostMapping(value = "/pay/add")
    public Object addPay(PayDTO payDTO);

    @GetMapping(value = "/pay/get/{id}")
    public Object getPayInfo(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/getAll")
    public Object getPayList();

    @GetMapping(value = "/pay/get/info")
    public String getInfoByConsul();

    /**
     * Resilience4J CircuitBreaker 例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id);

    /**
     * Resilience4J bulkhead 例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/ratelimit/{id}")
    public Object myRateLimit(@PathVariable("id") Integer id);

    /**
     * Micrometer(Sleuth)进行链路追踪监控
     * @param id
     * @return
     */
    @GetMapping("/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);

    /**
     * gateway 网关测试1
     * @param id
     * @return
     */
    @GetMapping("/pay/gateway/get/{id}")
    public Object getGatewayById(@PathVariable("id") Integer id);

    /**
     * gateway 网关测试2
     * @return
     */
    @GetMapping("/pay/gateway/info")
    public Object getGatewayInfo();

    /**
     * gateway 网关测试3
     * @param request
     * @return
     */
    @GetMapping("/pay/gateway/filter")
    public Object getGatewayFilter(HttpServletRequest request);
}
