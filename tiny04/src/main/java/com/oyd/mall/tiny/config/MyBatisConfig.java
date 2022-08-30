package com.oyd.mall.tiny.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.oyd.mall.tiny.mbg.mapper")
public class MyBatisConfig {
}
