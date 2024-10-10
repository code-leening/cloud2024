package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author leening
 * @create 2024-07-31-11:03
 */
//Main8001的SpringBootApplication启动类
@SpringBootApplication
//主启动类中一次注解，所有mapper接口都不用注解，直接使用
@MapperScan("com.atguigu.cloud.mapper")//import tk.mybatis.spring.annotation.MapperScan;
@EnableDiscoveryClient//激活consul的注解-注册发现
@RefreshScope //动态刷新
public class Main8001 {

    public static void main(String[] args) {
        SpringApplication.run(Main8001.class,args);
    }
}