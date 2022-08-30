package com.oyd.mall.tiny.dto;

import lombok.Getter;

@Getter
public enum QueueEnum {
    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("mall.order.direct","mall.order.cancel","mall.order.cancel"),
    QUEUE_TTL_ORDER_CANCEL("mall.order.direct.ttl","mall.order.cancel.ttl","mall.order.cancel.ttl")

    ;

    private String exchange;
    private String queueName;
    private String routingKey;

    QueueEnum(String exchange, String queueName, String routingKey) {
        this.exchange = exchange;
        this.queueName = queueName;
        this.routingKey = routingKey;
    }
}
