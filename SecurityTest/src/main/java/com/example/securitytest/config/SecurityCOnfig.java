package com.example.securitytest.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityCOnfig extends WebSecurityConfigurerAdapter {

    @Bean
    IgnoreUrlConfig ignoreUrlConfig() {
        return new IgnoreUrlConfig();
    }
}
