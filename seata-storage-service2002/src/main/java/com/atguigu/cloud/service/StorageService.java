package com.atguigu.cloud.service;

/**
 * @author leening
 * @create 2024-09-27-08:23
 */
public interface StorageService {
    /**
     * 扣减库存
     * @param productId
     * @param count
     */
    void decrease(Long productId, Integer count);
}
