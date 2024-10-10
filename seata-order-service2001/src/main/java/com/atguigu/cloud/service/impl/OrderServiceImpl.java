package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.apis.AccountFeignApi;
import com.atguigu.cloud.apis.StorageFeignApi;
import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.mapper.OrderMapper;
import com.atguigu.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author leening
 * @create 2024-09-26-15:37
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Resource
    private OrderMapper orderMapper;
    @Resource //订单微服务通过OpenFeign去调用库存微服务
    private StorageFeignApi storageFeignApi;
    @Resource //订单微服务通过OpenFeign去调用账户微服务
    private AccountFeignApi accountFeignApi;

    @Override
    @GlobalTransactional(name = "yy-create-order",rollbackFor = Exception.class) // 开启Seata 的AT模式保证数据一致性
    public Object creat(Order order) {

        // xid 全局事务id检查-重要
        String xid = RootContext.getXID();

        //1 新建订单
        log.info("---------开始新建订单："+"\t"+"xid:"+xid);
        //订单新建时默认初始订单状态为零
        order.setStatus(0);
        int result = orderMapper.insertSelective(order);
        //插入订单成功后，获取插入mysql的实体对象
        Order orderFromDB = null;
        if(result > 0){
            //从mysql里边查出刚插入的记录
            orderFromDB = orderMapper.selectOne(order);
            log.info("---------> 新建订单成功：orderFromDB info："+orderFromDB);
            System.out.println();

            //2 扣减库存
            log.info("---------> 订单微服务开始调用Storage库存：做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(),orderFromDB.getCount());
            log.info("---------> 订单微服务结束调用Storage库存：做扣减完成");
            System.out.println();

            //3 扣减账户
            log.info("---------> 订单微服务开始调用Account库存：做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(),orderFromDB.getMoney());
            log.info("---------> 订单微服务结束调用Account库存：做扣减完成");
            System.out.println();

            //4 修改订单状态
            // 订单状态从零改为1，表示已经完成
            log.info("---------> 修改订单状态");
            orderFromDB.setStatus(1);

            //whereCondition mybatis工具中带条件的类（表中查询时，where等各种条件的拼接）
            Example whereCondition = new Example(Order.class);
            Example.Criteria criteria = whereCondition.createCriteria();
            criteria.andEqualTo("userId", orderFromDB.getUserId());
            criteria.andEqualTo("status", 0);

            int updateResult = orderMapper.updateByExampleSelective(orderFromDB, whereCondition);
            log.info("---------> 修改订单状态修改完成"+"\t"+"updateResult:"+updateResult);
            log.info("---------> orderFromDB info："+orderFromDB);

        }
        System.out.println();
        log.info("---------结束新建订单："+"\t"+"xid:"+xid);
        return orderFromDB;
    }
}
