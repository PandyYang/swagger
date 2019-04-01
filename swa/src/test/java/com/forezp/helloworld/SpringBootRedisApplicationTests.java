package com.forezp.helloworld;

import com.forezp.helloworld.dao.RedisDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Author: Pandy
 * @Date: 2019/3/31 10:16
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootRedisApplicationTests {
    @Test
    public void contextLoads(){

    }
    @Autowired
    private RedisDao redisDao;
    @Test
    public void testRedis(){
        redisDao.setKey("name","Pandy");
        redisDao.setKey("age","22");
        log.info(redisDao.getValue("name"));
        log.info(redisDao.getValue("age"));
    }
}
