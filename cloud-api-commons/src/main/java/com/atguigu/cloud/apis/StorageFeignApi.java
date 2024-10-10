package com.atguigu.cloud.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author leening
 * @create 2024-09-26-10:14
 */
@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {

    /**
     *扣减库存
     */
    @RequestMapping(value = "/storage/decrease")
    Object decrease(@RequestParam("productId") Long productId,@RequestParam("count") Integer count);
}
