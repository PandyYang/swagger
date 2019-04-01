package com.forezp.helloworld.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Pandy
 * @Date: 2019/3/31 10:12
 * @Version 1.0
 */
@Repository
public class RedisDao {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //设值
    public void setKey(String key,String value){
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value,1,TimeUnit.MINUTES);//1分钟过期
    }
    //取值
    public String getValue(String key){
        ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
        return ops.get(key);
    }
}
