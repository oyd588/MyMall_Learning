package com.oyd.mall.tiny.config;

import com.oyd.mall.tiny.dto.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Configuration
public class RabbitMqConfig {

    /**
     * 订单消息实际消费队列所绑定的交换机
     */
//    @Bean("QUEUE_ORDER_CANCEL_EXCHANGE")
//    public DirectExchange orderDirectExchange() {
//        return ExchangeBuilder.directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
//                .durable(true)
//                .build();
//    }

    /**
     * 订单消息实际消费队列
     */
//    @Bean("QUEUE_ORDER_CANCEL_QUEUE")
//    public Queue orderCancelQueue() {
//        return QueueBuilder.durable(QueueEnum.QUEUE_ORDER_CANCEL.getQueueName()).build();
//    }

    /**
     * 消费队列绑定消费交换机
     */
//    @Bean
//    public Binding binding1(@Qualifier("QUEUE_ORDER_CANCEL_EXCHANGE") DirectExchange directExchange, @Qualifier("QUEUE_ORDER_CANCEL_QUEUE") Queue queue) {
//        return BindingBuilder.bind(queue).to(directExchange).with(QueueEnum.QUEUE_ORDER_CANCEL.getRoutingKey());
//    }

    /**
     * 订单延迟队列所绑定的交换机
     */

//    public DirectExchange orderTtlDirect() {
//        return ExchangeBuilder.directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
//                .durable(true)
//                .build();
//    }
    @Bean("QUEUE_ORDER_CANCEL_TTL_EXCHANGE")
    public CustomExchange orderTtlDirect(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-delayed-type", "direct");
        return new CustomExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(), "x-delayed-message", true, false, map);
    }

    /**
     * 订单延迟队列（死信队列）
     */
    @Bean("QUEUE_ORDER_CANCEL_TTL_QUEUE")
    public Queue orderTtlQueue() {
        return QueueBuilder.durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getQueueName())
//                .deadLetterExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
//                .deadLetterRoutingKey(QueueEnum.QUEUE_ORDER_CANCEL.getRoutingKey())
                .build();
    }

    /**
     * 绑定延迟队列到交换机
     */
    @Bean
    public Binding orderTtlBinding(@Qualifier("QUEUE_ORDER_CANCEL_TTL_EXCHANGE") CustomExchange customExchange,
                                   @Qualifier("QUEUE_ORDER_CANCEL_TTL_QUEUE") Queue queue) {
        return BindingBuilder.bind(queue).to(customExchange).with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRoutingKey()).noargs();
    }

}
