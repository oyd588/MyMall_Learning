package com.oyd.controller;

import com.oyd.pojo.Person;
import com.oyd.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class MybatisController {


    @Autowired
    private PersonService personService;

    @GetMapping("/person/{id}")
    public Object getPersonById(@PathVariable("id") Integer id) {


        ExecutorService es = Executors.newFixedThreadPool(200);
        for (int i = 0; i < 500; i++) {
            es.submit(new Runnable() {
                @Override
                public void run() {
                    personService.getPerson(id);
                }
            });
        }
        return personService.getPerson(id);

    }
}
