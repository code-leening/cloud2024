package com.atguigu.cloud.controller;

import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leening
 * @create 2024-09-27-09:03
 */
@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @RequestMapping("/account/decrease")
    public ResultData<Object> deposit(@RequestParam("userId") Long userId, @RequestParam("money") Long money) {
        accountService.decrease(userId,money);
        return ResultData.success("扣减账户余额成功");
    }
}
