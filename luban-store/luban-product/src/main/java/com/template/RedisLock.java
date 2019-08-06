package com.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCommands;

import java.util.UUID;

/**
 * @Author 李非凡
 * @Description:
 * Redis锁
 * @Date 2019/7/24 15:45
 * @Version 1.0
 */
@Component
public class RedisLock {

    @Autowired
    private RedisTemplate redisTemplate;

    // 键前缀
    private static final String KEY_PREFIX = "lock";

    // 解锁
    public static final String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    // ThreadLocal用于保存某个线程共享变量：对于同一个static ThreadLocal，不同线程只能从中get，set，remove自己的变量，而不会影响其他线程的变量
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 加锁
     * @param key
     */
    public void lock(String key){
        boolean b = tryLock(key);
        if (b){
            return;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock(key);
    }

    /**
     * 判断是否加锁
     * @param key
     * @return
     */
    public boolean tryLock(String key){
        String uuid = UUID.randomUUID().toString();
        RedisCallback<String> callback = (connection)->{
            JedisCommands commands = (JedisCommands) connection.getNativeConnection();
            return commands.set(KEY_PREFIX + key, uuid, "NX", "PX", 60000);
        };
        Object execute = redisTemplate.execute(callback);
        if (execute != null){
            threadLocal.set(uuid);
            return true;
        }
        return false;
    }

    /**
     * 释放锁
     * @param key
     */
    public void unLock(String key){
        RedisCallback redisScript = new RedisCallback() {
            @Nullable
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Object eval = redisConnection.eval(UNLOCK_LUA.getBytes(),
                        ReturnType.fromJavaType(Long.class),1,
                        (KEY_PREFIX + key).getBytes(),
                        threadLocal.get().getBytes());
                return eval;
            }
        };
        redisTemplate.execute(redisScript);
    }
}
