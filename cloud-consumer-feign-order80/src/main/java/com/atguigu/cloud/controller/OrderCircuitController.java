package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.resp.ResultData;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author leening
 * @create 2024-08-08-15:00
 */
@RestController
@RequestMapping("/feign")
public class OrderCircuitController {

    @Resource
    private PayFeignApi payFeignApi;


    /**
     * CircuitBreaker 段容器 例子
     * @param id
     * @return
     */
    @GetMapping("/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "fallback4CircuitBreaker")
    public Object getPayById4CircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    /**
     * Bulkhead 舱壁隔离 例子（SemaphoreBulkhead （信号量舱壁））
     * @param id
     * @return
     */
    @GetMapping("/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "fallback4Bulkhead", type = Bulkhead.Type.SEMAPHORE)
    public Object getPayById4Bulkhead(@PathVariable("id") Integer id) {
        System.out.println(Thread.currentThread().getName() + " into ...");
        System.out.println(Thread.currentThread().getName() + " over ...");
        return payFeignApi.myBulkhead(id);
        //return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id));
    }

    /**
     * Bulkhead 舱壁隔离 例子（FixedThreadPoolBulkhead （固定线程池舱壁））
     * @param id
     * @return
     */
    @GetMapping("/pay/bulkhead/pool/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "fallback4BulkheadThreadPool", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<Object> getPayById4BulkheadThreadPool(@PathVariable("id") Integer id) {
        System.out.println(Thread.currentThread().getName() + " 开始进入 ...");
        try {TimeUnit.SECONDS.sleep(3);}catch (InterruptedException e){e.printStackTrace();}
        System.out.println(Thread.currentThread().getName() + " 准备离开 ...");
        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t" + "Bulkhead.Type.THREADPOOL");
    }

    @GetMapping("/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "fallback4RateLimit")
    public Object getPayById4RateLimit(@PathVariable("id") Integer id) {
        return payFeignApi.myRateLimit(id);
    }

    //服务降级后的兜底工作
    public ResultData<String> fallback4CircuitBreaker(Throwable throwable) {
        return ResultData.fail("系统繁忙, 请稍后重试...");
    }

    public ResultData<String> fallback4Bulkhead(Throwable throwable) {
        return ResultData.fail("超出最大请求数量限制, 请稍后重试...");
    }

    public CompletableFuture<ResultData<Object>> fallback4BulkheadThreadPool(Throwable throwable) {
        return CompletableFuture.supplyAsync(() -> ResultData.fail("Bulkhead.Type.THREADPOOL! 超出最大请求数量限制, 请稍后重试..."));
    }

    public ResultData<String> fallback4RateLimit(Throwable throwable) {
        return ResultData.fail("服务器限流, 请稍后重试...");
    }
}
