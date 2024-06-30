package com.example.quizz.service;

public interface RedisService {
    void set(String key,Object value,Long expiration);
    Object get(String key);
}
