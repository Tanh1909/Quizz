package com.example.quizz.service.serviceImpl;

import com.example.quizz.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public void set(String key, Object value, Long expiration) {
        redisTemplate.opsForValue().set(key,value,expiration, TimeUnit.MILLISECONDS);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
