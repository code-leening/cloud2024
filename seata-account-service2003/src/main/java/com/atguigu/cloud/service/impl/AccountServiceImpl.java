package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.mapper.AccountMapper;
import com.atguigu.cloud.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author leening
 * @create 2024-09-27-08:52
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    /**
     * 扣减账户余额
     * @param userId
     * @param money
     */
    @Override
    public void decrease(Long userId, Long money) {
        log.info("--------> account-service 中扣减账户余额开始");
        accountMapper.decrease(userId, money);
        myTimeOut();
//        int age = 10/0
        log.info("--------> account-service 中扣减账户余额结束end");
    }

    private static void myTimeOut(){
        try{
            TimeUnit.SECONDS.sleep(65);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
