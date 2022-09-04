package com.example.securitytest.CustomProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(value = CustomProperties.class)
@ConfigurationProperties(value = "custom")
public class CustomProperties {

    /**
     * 标题描述
     */
    private String title;
}
