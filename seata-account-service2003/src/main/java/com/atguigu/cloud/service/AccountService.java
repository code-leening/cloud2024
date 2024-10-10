package com.atguigu.cloud.service;

import org.apache.ibatis.annotations.Param;

/**
 * @author leening
 * @create 2024-09-27-08:50
 */
public interface AccountService {

    /**
     * 扣减账户余额
     * @param userId
     * @param money
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}
