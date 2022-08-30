package com.oyd.mall.tiny.controller;

import com.oyd.mall.tiny.dto.OrderParam;
import com.oyd.mall.tiny.dto.QueueEnum;
import com.oyd.mall.tiny.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
@Api(tags = "OmsPortalOrderController", description = "订单管理")
public class OmsPortalOrderController {

    @Autowired
    private OmsPortalOrderService omsPortalOrderService;

    @ApiOperation("根据购物车信息生成订单")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public Object generateOrder(@RequestBody OrderParam orderParam) {
        return omsPortalOrderService.generateOrder(orderParam);
    }

}
