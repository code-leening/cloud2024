package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author leening
 * @create 2024-07-31-15:34
 */
@RestController
@Slf4j //输出和日志
@Tag(name = "支付微服务模块",description = "支付增删改查")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法，json串作为参数")
    public ResultData <String> addPay(@RequestBody Pay pay){
        System.out.printf(pay.toString());
        int i = payService.add(pay);
        return ResultData.success("成功插入记录，返回值：" + i);
    }

    @PostMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法，id作为参数")
    public ResultData <Integer> deletePay(@PathVariable("id") Integer id){
        int i = payService.delete(id);
        return ResultData.success(i);
    }

    @PutMapping(value = "/pay/update")
    @Operation(summary = "更新",description = "更新支付流水方法，json串作为参数")
    public ResultData <String> updatePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        int i = payService.update(pay);
        return ResultData.success("成功更新记录，返回值：" + i);
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "查询单个支付流水信息",description = "查询支付流水方法，id作为参数")
    public ResultData <Pay> getById(@PathVariable("id") Integer id){
        if (id < 0) throw new RuntimeException("id不能为负数");
//        try {
//            TimeUnit.SECONDS.sleep(60000);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping(value = "/pay/getAll")
    @Operation(summary = "查询所有支付流水列表")
    public ResultData <List<Pay>> getPayList(){
        List<Pay> payList = payService.getAll();
        return ResultData.success(payList);
    }

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "pay/get/info")
    public String getInfoByConsul(@Value("${atguigu.info}") String info){
        return "consul配置信息：" + info + "port:" + port;
    }

}
