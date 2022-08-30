package com.oyd.service.impl;

import com.oyd.config.RedisConfig;
import com.oyd.mybatis.PersonMapper;
import com.oyd.pojo.Person;
import com.oyd.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PersonMapper personMapper;

//    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE,key = "'pms:brand:' + #id")
    @Override
    public Object getPerson(Integer id) {
        String key = "user:" + id;
        //先从缓存查找

        Object obj = redisTemplate.opsForValue().get(key);

        if (obj == null) {
            synchronized (this.getClass()) {
                obj = redisTemplate.opsForValue().get(key);
                if (obj == null) {
                    //如果缓存没有，从数据库查，并存入缓存
                    log.debug("======================从数据库查找===================");
                    Person personFromDb = personMapper.selectByPrimaryKey(id);
                    redisTemplate.opsForValue().set(key, personFromDb);
                    return personFromDb;
                }else {
                    log.debug("======================(同步代码块）从缓存查找===================");
                    return obj;
                }

            }
        } else {
            log.debug("======================从缓存查找===================");
            return obj;
        }
//        return personMapper.selectByPrimaryKey(id);
    }

    @Override
    public int setPerson(Person person) {
        return personMapper.insert(person);
    }
}
