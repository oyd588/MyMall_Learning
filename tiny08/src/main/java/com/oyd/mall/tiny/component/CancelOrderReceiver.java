package com.oyd.mall.tiny.component;

import com.oyd.mall.tiny.service.OmsPortalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 延迟队列消费者
 */
@Slf4j
@Component
public class CancelOrderReceiver {

    @Autowired
    private OmsPortalOrderService portalOrderService;

    @RabbitListener(queues = "mall.order.cancel.ttl")
    public void hanler(Message message,Long orderId) {

        log.info("receive delay message orderId:{}", orderId);
       portalOrderService.cancelOrder(orderId);
    }
}
