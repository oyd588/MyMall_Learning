package com.oyd.mall.tiny.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = {"com.oyd.mall.tiny.mbg.mapper","com.oyd.mall.tiny.dao"})
public class MyBatisConfig {
}
