package com.atguigu.cloud.controller;

import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leening
 * @create 2024-09-27-08:29
 */
@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    @RequestMapping("/storage/decrease")
    public ResultData<Object> decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return ResultData.success("扣减库存成功");
    }
}
