package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Order;

/**
 * @author leening
 * @create 2024-09-26-15:35
 */
public interface OrderService {
    /**
     * 创建订单
     * @param order
     */
    Object creat(Order order);
}
