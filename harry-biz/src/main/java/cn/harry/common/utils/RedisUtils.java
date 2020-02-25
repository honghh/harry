package cn.harry.common.utils;

import cn.harry.config.RedisConfig;
import cn.hutool.core.date.DateUtil;
import com.google.gson.Gson;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author honghh
 * Date 2019/07/18 14:03
 * Copyright (C) www.honghh.top
 */
@Component
public class RedisUtils {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private ValueOperations<String, String> valueOperations;
    @Resource
    private HashOperations<String, String, Object> hashOperations;
    @Resource
    private ListOperations<String, Object> listOperations;
    @Resource
    private SetOperations<String, Object> setOperations;
    @Resource
    private ZSetOperations<String, Object> zSetOperations;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisConfig redisConfig;

    /**
     * 不设置过期时长
     */
    private final static long NOT_EXPIRE = -1;
    /**
     * 默认编码
     */
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final static Gson GSON = new Gson();

    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value) {
        set(key, value, redisConfig.getExpire());
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return GSON.toJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    public boolean exists(final String key) {
        return stringRedisTemplate.execute((RedisCallback<Boolean>) connection -> connection.exists(key.getBytes(DEFAULT_CHARSET)));
    }

    /**
     * 自增操作
     *
     * @param delta 自增步长
     */
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 自增操作
     */
    public Long increment(String prefix, String key) {
        Long i = stringRedisTemplate.opsForValue().increment(prefix + key);
        if (i != null && i == 1L) {
            String key2 = new SimpleDateFormat("yyyyMMdd").format(DateUtil.yesterday());
            System.out.println("key2:  "+key2);
            delete(prefix + key2);
        }
        return i;
    }
}
