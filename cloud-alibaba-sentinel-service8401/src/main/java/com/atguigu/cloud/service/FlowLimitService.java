package com.atguigu.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author leening
 * @create 2024-08-27-10:42
 */
@Service
public class FlowLimitService
{
    @SentinelResource("common")
    public void common()
    {
        System.out.println("------- FlowLimitService come in -------");
    }

}
