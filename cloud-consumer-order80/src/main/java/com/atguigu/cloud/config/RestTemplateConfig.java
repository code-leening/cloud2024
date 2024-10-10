package com.atguigu.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author leening
 * @create 2024-08-01-15:10
 */
@Configuration // 开启配置类
public class RestTemplateConfig {

    @Bean
    @LoadBalanced //微服务是分布式，多个服务器，需要添加注解-负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
