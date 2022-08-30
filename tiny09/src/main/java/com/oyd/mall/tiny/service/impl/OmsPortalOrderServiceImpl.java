package com.oyd.mall.tiny.service.impl;

import com.oyd.mall.tiny.common.api.CommonResult;
import com.oyd.mall.tiny.component.CancelOrderSender;
import com.oyd.mall.tiny.dto.OrderParam;
import com.oyd.mall.tiny.service.OmsPortalOrderService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@Slf4j
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {

    @Value("${spring.rabbitmq.delayTime}")
    private Integer delayTimes;

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        //todo 执行一系列下单操作，具体参考mall项目
        log.info("process generateOrder");
        //下单完成后开启一个延迟消息，用于当用户一定时间内没有付款时取消订单
        //OrderId在下单后生成，此处模拟生成
        long orderId = 11L;
        sendDelayMessage(orderId);
        return CommonResult.success("下单成功");

    }

    @Override
    public void cancelOrder(Long orderId) {
        //todo 执行一系列取消订单操作
        log.info("process cancelOrder ,orderId：{} ", orderId);
    }

    private void sendDelayMessage(Long orderId) {
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }
}
