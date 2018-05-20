package com.orange.onlinetest.cache;

import com.orange.onlinetest.model.Question;
import com.sun.xml.internal.ws.util.ASCIIUtility;
import org.apache.ibatis.ognl.ASTConst;
import org.apache.tomcat.util.buf.Ascii;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by apple on 2018/4/18.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionCacheTest {
    @Autowired
    private QuestionCache questionCache;
    @Test
    public void hset() throws Exception {
        Map<Integer,Question> map = new HashMap<>();
//        map.put(1,new Question(1,new Date(),new Date(),"this is the first test","nice",1,"ss",10,1));
        questionCache.hset(map,new com.orange.onlinetest.model.Test());
    }

    @Test
    public void hget() throws Exception {
        Question question = questionCache.hGetQuestion(1,1);
        if (question != null){
            System.out.println(question.toString());
        }
    }

}