package com.oyd;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Hello world!
 */
@MapperScan("com.oyd.mybatis")
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
