package com.oyd.mall.tiny.service;

import io.swagger.models.auth.In;

/**
 * redis操作Service,
 * 对象和数组都以json形式进行存储
 */
public interface RedisService {

    /**
     * 存储数据
     */
    void set(String key,String value);

    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 设置ttl
     */
    boolean expire(String key, long exipre);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     * @param delta 自增步长
     * @return 自增后的数值
     */
    Long increment(String key,long delta);
}
