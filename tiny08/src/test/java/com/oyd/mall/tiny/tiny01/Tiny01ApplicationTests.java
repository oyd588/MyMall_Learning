package com.oyd.mall.tiny.tiny01;

import com.oyd.mall.tiny.dto.QueueEnum;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Tiny01ApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        rabbitTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(), QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRoutingKey(), "延时10s的消息".getBytes(), new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties messageProperties = message.getMessageProperties();
                messageProperties.setExpiration("10000");
                return message;
            }
        });
        System.out.println("发送了延时10s的消息");
        rabbitTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(), QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRoutingKey(), "延时2s的消息".getBytes(), new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties messageProperties = message.getMessageProperties();
                messageProperties.setExpiration("2000");
                return message;
            }
        });
        System.out.println("发送了延时2s的消息");
    }

}
