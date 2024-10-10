package com.atguigu.cloud.apis;

import cn.hutool.log.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author leening
 * @create 2024-09-26-11:06
 */
@FeignClient(value = "seata-account-service")
public interface AccountFeignApi {
    /**
     *扣减账户余额
     */
    @RequestMapping("/account/decrease")
    Object decrease(@RequestParam("userId")Long userId, @RequestParam("money") Long money);
}
