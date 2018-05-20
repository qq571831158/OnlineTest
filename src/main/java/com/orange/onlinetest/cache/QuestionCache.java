package com.orange.onlinetest.cache;

import com.orange.onlinetest.model.Question;
import com.orange.onlinetest.model.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

/**
 * Created by apple on 2018/4/18.
 */
@Component
public class QuestionCache implements InitializingBean{

    private static final Logger logger = LoggerFactory.getLogger(QuestionCache.class);

    public static final String KEY_QUESTIONS_PREFIX = "TEST_";

    public static final String SUBKEY_QUESTION_ID = "QID";

    public static final String SUBKEY_TEST_ID = "testId";

    public static final String SUBKEY_TEST_PAPER_NAME = "testPaperName";

    public static final String SUBKEY_TEST_SCORE= "testScore";

    public static final String SUBKEY_QUESTION_LENGTH = "questionLength";

    public static final String SUBKEY_TEST_END_TIME = "testEndTime";

    private JedisPool jedisPool;

    @Override
    public void afterPropertiesSet() throws Exception {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setTestOnBorrow(true);
        config.setMaxIdle(8);
        jedisPool = new JedisPool(config,"127.0.0.1",6379,10000,"123456");
    }

    public void hset(Map<Integer,Question> map, Test test){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            for (Map.Entry<Integer,Question> entry : map.entrySet()){
                jedis.hset((KEY_QUESTIONS_PREFIX + test.getId()).getBytes(), (SUBKEY_QUESTION_ID + entry.getKey()).getBytes(),SerializeUtil.serialize(entry.getValue()));
            }
            jedis.hset(KEY_QUESTIONS_PREFIX + test.getId(),SUBKEY_TEST_ID,test.getId()+"");
            jedis.hset(KEY_QUESTIONS_PREFIX + test.getId(),SUBKEY_TEST_PAPER_NAME,test.getTestPaperName());
            jedis.hset(KEY_QUESTIONS_PREFIX + test.getId(),SUBKEY_TEST_SCORE,test.getTestPaperScore() + "");
            jedis.hset(KEY_QUESTIONS_PREFIX + test.getId(),SUBKEY_QUESTION_LENGTH,test.getQuestions().split(Test.QUESTION_SPLIT_STRING).length + "");
            Long endTime = test.getStartTime().getTime() + 120 * 60 * 1000;
            jedis.hset(KEY_QUESTIONS_PREFIX + test.getId(),SUBKEY_TEST_END_TIME,endTime.toString());
        }catch (Exception e){
            logger.error("question hset error"+e.getMessage());
            e.printStackTrace();
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }

    public Question hGetQuestion(int testId,int num){
        Question question = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] questionByte = jedis.hget((KEY_QUESTIONS_PREFIX + testId).getBytes(),(SUBKEY_QUESTION_ID+ num).getBytes());
            question = (Question)SerializeUtil.unserialize(questionByte);
        }catch (Exception e){
            logger.error("question hget error"+e.getMessage());
            e.printStackTrace();
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return question;
    }

    public String  hGetKey(int testId,String key){
        String result = "";
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hget(KEY_QUESTIONS_PREFIX + testId,key);
        }catch (Exception e){
            logger.error("question hget error"+e.getMessage());
            e.printStackTrace();
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return result;
    }
}
