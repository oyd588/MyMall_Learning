package com.example.securitytest;

import com.example.securitytest.config.IgnoreUrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityTestApplication {



    public static void main(String[] args) {
        SpringApplication.run(SecurityTestApplication.class, args);
    }

}
