package com.example.securitytest.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlConfig {

    private List<String> urls = new ArrayList<>();
}
