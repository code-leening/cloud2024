package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leening
 * @create 2024-08-07-09:55
 */
@Configuration
public class FeignConfig {
    @Bean
    public Retryer myRetryer()
    {
        return Retryer.NEVER_RETRY;//Feign默认配置
        //最大请求次数3（1+2），初始间隔时间100ms，重试时间最大间隔为1s
        //return new Retryer.Default(100, 1, 3);
    }

    @Bean
    Logger.Level feignLoggerLevel (){
        return Logger.Level.FULL;
    }
}
