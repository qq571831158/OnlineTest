package com.orange.onlinetest.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/16 15:10
 */
@Service
public class JedisAdapter implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);

    public  JedisPool pool = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("redis://localhost:6379/10");
    }
    public long sadd(String key,String value){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sadd(key,value);
        }catch (Exception e){
            logger.error("发送异常"+e.getMessage());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return 0;
    }



    public long srem(String key,String value){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.srem(key,value);
        }catch (Exception e){
            logger.error("发送异常"+e.getMessage());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return 0;
    }

    public long scard(String key){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.scard(key);
        }catch (Exception e){
            logger.error("发送异常"+e.getMessage());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return 0;
    }

    public boolean sismember(String key,String value){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sismember(key,value);
        }catch (Exception e){
            logger.error("发送异常"+e.getMessage());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    public long lpush(String key,String value){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lpush(key,value);
        }catch (Exception e){
            logger.error("发送异常"+e.getMessage());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return 0;
    }

    public Object brpop( String key){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.rpop(key);
        }catch (Exception e){
            logger.error("发送异常"+e.getMessage());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public List<String> brpop(int timeout, String key){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.brpop(timeout,key);
        }catch (Exception e){
            logger.error("发送异常"+e.getMessage());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public void set(String key,String value){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set(key,value);
        }catch (Exception e){
            logger.error("发送异常"+e.getMessage());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }

    public String get(String key){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = pool.getResource();
            result = jedis.get(key);
        }catch (Exception e){
            logger.error("发送异常"+e.getMessage());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return result;
    }

}
