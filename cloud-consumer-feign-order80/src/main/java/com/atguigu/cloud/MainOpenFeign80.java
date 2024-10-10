package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author leening
 * @create 2024-08-05-18:55
 */
@SpringBootApplication
@EnableDiscoveryClient //consul为注册中心时-注册服务
@EnableFeignClients //启用feign客户端，定义服务+绑定接口，以下声明式的方法优雅而简单的实现服务调用
public class MainOpenFeign80 {
    public static void main(String[] args) {
        SpringApplication.run(MainOpenFeign80.class, args);
    }
}