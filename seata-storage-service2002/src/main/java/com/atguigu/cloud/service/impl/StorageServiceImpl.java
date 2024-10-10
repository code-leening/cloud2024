package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.mapper.StorageMapper;
import com.atguigu.cloud.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author leening
 * @create 2024-09-27-08:26
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    /**
     * 扣减库存
     * @param productId
     * @param count
     */
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("--------> storage-service中扣减库存开始");
        storageMapper.decrease(productId, count);
        log.info("--------> storage-service中扣减库存结束end");
    }
}
