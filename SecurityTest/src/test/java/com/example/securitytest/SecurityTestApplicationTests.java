package com.example.securitytest;

import com.example.securitytest.config.IgnoreUrlConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityTestApplicationTests {
    @Autowired
    private IgnoreUrlConfig ignoreUrlConfig;

    @Test
    void contextLoads() {
        System.out.println(ignoreUrlConfig);
    }

}
