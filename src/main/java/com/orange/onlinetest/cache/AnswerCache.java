package com.orange.onlinetest.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

/**
 * Created by apple on 2018/5/14.
 */
@Component
public class AnswerCache implements InitializingBean{

    private static final Logger logger = LoggerFactory.getLogger(AnswerCache.class);

    public static final String KEY_STU_TEST_ANSWER_PREFIX = "ANSWER_";

    private JedisPool jedisPool;

    @Override
    public void afterPropertiesSet() throws Exception {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setTestOnBorrow(true);
        config.setMaxIdle(8);
        jedisPool = new JedisPool(config,"127.0.0.1",6379,10000,"123456");
    }

    public void hsetAnswer(String testId,String stuId,String qId,String answer){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(genernateKey(testId,stuId),qId,answer);
        }catch (Exception e){
            logger.error("question hset error"+e.getMessage());
            e.printStackTrace();
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }

    public String hgetAnswer(Integer testId,Integer stuId,Integer qId){
        String result = "";
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result =  jedis.hget(genernateKey(testId.toString(),stuId.toString()),qId.toString());
        }catch (Exception e){
            logger.error("hgetAnswer error" + e.getMessage());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return result;
    }

    public Map<Integer,String> hgetAllAnswer(Integer testId,Integer stuId){
        Jedis jedis = null;
        Map<String,String> map = new HashMap<>();
        try {
            jedis = jedisPool.getResource();
            map = jedis.hgetAll(genernateKey(testId.toString(),stuId.toString()));
        }catch (Exception e){
            logger.error("hgetAllAnswer error" + e.getMessage());
        }
        return mapString2Integer(map);
    }

    private String genernateKey(String testId,String stuId){
        return KEY_STU_TEST_ANSWER_PREFIX + testId + "_" + stuId;
    }

    private Map<Integer,String> mapString2Integer(Map<String,String> srcMap){
        Map<Integer,String> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<String,String> entry : srcMap.entrySet()){
            if (entry.getKey() != null && !entry.getKey().equals("")){
                list.add(Integer.parseInt(entry.getKey()));
            }
        }
        Collections.sort(list);
        for (Integer i : list){
            map.put(i,srcMap.get(i.toString()));
        }
        return map;
    }
}
