package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author leening
 * @create 2024-08-22-15:05
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Main83 {
    public static void main(String[] args) {
        SpringApplication.run(Main83.class, args);
    }
}