package com.atguigu.cloud.mapper;

import com.atguigu.cloud.entities.Account;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface AccountMapper extends Mapper<Account> {
    /**
     * 扣减库存
     * @param userId
     * @param money
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}