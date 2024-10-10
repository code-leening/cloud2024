package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author leening
 * @create 2024-08-01-15:14
 */
@RestController
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/feign/pay/add")
    public Object addOrder(@RequestBody PayDTO payDTO)
    {
        System.out.println("第一步:模拟本地oaddrder新增订单成功（省略sql操作），第二步：在开启addpay支付服务远程调用");;
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping(value = "/feign/pay/get/{id}")
    public Object getPayInfo(@PathVariable("id") Integer id)
    {
        System.out.println("---支付微服务远程调用，按照id查询订单支付流水信息");
        Object payInfo = null;
        try {
            System.out.println("调用开始：" + DateUtil.now());
            payInfo = payFeignApi.getPayInfo(id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("调用结束：" + DateUtil.now());
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), ReturnCodeEnum.RC500.getMessage());
        }
        return payInfo;
    }

    @GetMapping(value = "/feign/pay/getAll")
    public Object getPayList()
    {
        return payFeignApi.getPayList();
    }

    @GetMapping(value = "/feign/pay/get/info")
    public Object getInfoByConsul(){
        return payFeignApi.getInfoByConsul();
    }
}
