package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StringRedisTemplate redis;

    @GetMapping("/")
    public String hello_default() {
        return "<body bgcolor='blue'>Spring Boot START PAGE CHANGED! MK4!!!!</body>";
    }

     @GetMapping("/mysql")
    public String dbTest() {
        try {
            String sql = "SELECT now()";
            String result = jdbcTemplate.queryForObject(sql, 
String.class);
            return "Database test successful. now() : " + result;
        } catch (Exception e) {
            e.printStackTrace();
            return "Database connection failed! Error: " + 
e.getMessage();
        }
    }
    # test mysql2
    @GetMapping("/mysql2")
    public String dbTest() {
        try {
            String sql = "SELECT id from member";
            String result = jdbcTemplate.queryForObject(sql, 
String.class);
            return "Database test successful. now() : " + result;
        } catch (Exception e) {
            e.printStackTrace();
            return "Database connection failed! Error: " + 
e.getMessage();
        }
    }

    @GetMapping("/redis-set")
    public String redisSet() {
        try {
            redis.opsForValue().set("key", "100");
            return "Redis SET OK. key=key, value=100";
        } catch (Exception e) {
            e.printStackTrace();
            return "Redis SET failed! Error: " + e.getMessage();
        }
    }

    @GetMapping("/redis-get")
    public String redisGet() {
        try {
            String value = redis.opsForValue().get("key");
            return "Redis GET OK. key=key >> " + value;
        } catch (Exception e) {
            e.printStackTrace();
            return "Redis GET failed! Error: " + e.getMessage();
        }
    }
}