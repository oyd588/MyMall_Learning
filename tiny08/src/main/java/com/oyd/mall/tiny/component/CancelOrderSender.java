package com.oyd.mall.tiny.component;

import com.oyd.mall.tiny.dto.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CancelOrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Long orderId, Integer delayTime) {
        //给延迟队列发送信息
        rabbitTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(), QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRoutingKey(), orderId,
                correlationData -> {
                    correlationData.getMessageProperties().setDelay(delayTime);
                    return correlationData;
                });
        log.info("send delay message orderID:{},delayTime:{} s", orderId, delayTime / 1000);
    }
}
