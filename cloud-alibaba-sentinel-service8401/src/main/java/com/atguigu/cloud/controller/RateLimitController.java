package com.atguigu.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leening
 * @create 2024-08-27-15:39
 */
@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/rateLimit/byUrl")
    public String byUrl(){
        return "按rest地址限流测试";
    }

    @GetMapping("/rateLimit/byResource")
    @SentinelResource(value = "byResourceSentinelResource",blockHandler = "handlerBlockHandler")
    public String byResource(){
        return "按照资源名byResourceSentinelResource限流测试";
    }

    public String handlerBlockHandler(BlockException ex) {
        return "服务不可用，触发了@SentinelResource-handlerBlockHandler ******";
    }


    @GetMapping("/rateLimit/doAction/{p1}")
    @SentinelResource(value = "doActionSentinelResource",
            blockHandler = "doActionBlockHandler", fallback = "doActionFallback")
    public String doAction(@PathVariable("p1") Integer p1){
        return "doAction" + p1;
    }

    public String doActionBlockHandler(@PathVariable("p1") Integer p1, BlockException ex) {
        log.error("sentinel配置自定义限流了:{ }",ex);
        return "sentinel配置自定义限流了";
    }

    public String doActionFallback(@PathVariable("p1") Integer p1, BlockException ex) {
        log.error("程序逻辑了:{ }",ex);
        return "程序逻辑了" + "\t" + ex.getMessage();
    }

    /**
     * 热点参数限流
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "dealHandler_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "-------testHotKey------";
    }
    public String dealHandler_testHotKey(String p1,String p2,BlockException ex){
        return "------dealHandler_testHotKey------";
    }

}
