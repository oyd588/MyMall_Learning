package com.oyd;

import com.oyd.pojo.Person;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Applicationtest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {


        // 保存字符串
//        stringRedisTemplate.opsForValue().set("aaa", "111123");
//        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

        Person person = new Person();
        person.setName("欧阳东");
        person.setAge(19);
//        redisTemplate.opsForValue().set("oyd", person);
        Person oyd = (Person)redisTemplate.opsForValue().get("oyd");

        System.out.println(oyd);
    }


}
