package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author leening
 * @create 2024-08-22-14:51
 */
@RestController
public class PayAlibabaController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/pay/nacos/{id}")
    public String getPayInfo(@PathVariable("id") Integer id){
        return "nacos registry serverPost: " + serverPort + ", id: " + id;
    }



    //openfeign+sentinel进行服务降级和流量监控的整合处理
    @GetMapping("/pay/nacos/get/{orderNo}")
    @SentinelResource(value = "getPayByOrderNo",blockHandler = "handleBlockHandle")
    public ResultData<Object> getPayByOrderNo(@PathVariable("orderNo") String orderNo){
        //模拟从数据库查询出数组并赋值给DTO
        PayDTO payDTO = new PayDTO();
        payDTO.setOrderNo(orderNo);
        payDTO.setId(1024);
        payDTO.setAmount(BigDecimal.valueOf(9.9));
        payDTO.setPayNo("pay:" + IdUtil.fastUUID());
        payDTO.setUserId(1);
        return ResultData.success(payDTO);
    }
    public ResultData<Object> handleBlockHandle(@PathVariable("orderNo") String orderNo ,BlockException ex) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "getPayByOrderNo服务不可用，" +
                "触犯sentinel流控配置" + "\t"+"o(╥﹏╥)o");
    }

}
