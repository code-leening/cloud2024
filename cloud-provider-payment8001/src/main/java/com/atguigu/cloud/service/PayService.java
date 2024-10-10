package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Pay;

import java.util.List;

/**
 * @author leening
 * @create 2024-07-31-15:20
 */
public interface PayService {
    public int add(Pay pay);
    public int update(Pay pay);
    public int delete(Integer id);

    public Pay getById(Integer id);

    public List<Pay> getAll();
}
