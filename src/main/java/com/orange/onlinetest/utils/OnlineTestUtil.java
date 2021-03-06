package com.orange.onlinetest.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.orange.onlinetest.kafka.Message;
import com.orange.onlinetest.model.Answer;
import com.orange.onlinetest.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/9 8:32
 */
public class OnlineTestUtil {

    private static final Logger logger = LoggerFactory.getLogger(OnlineTestUtil.class);

    public static String getJSONString(int code,String msg){
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        return json.toJSONString();
    }

    public static String getJSONString(int code){
        JSONObject json = new JSONObject();
        json.put("code",code);
        return json.toJSONString();
    }

    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            logger.error("生成MD5失败", e);
            return null;
        }
    }

    public   static String getContents(String[] content){
        String contents = "";
        int charIndex = 65;
        for (String s: content){
            contents += (char)charIndex+":";
            contents += s;
            contents += Question.SELECTION_SPLIT_STRING;
            charIndex ++;
        }
        return contents;
    }

    public static String getAnswers(Integer[] answers){
        Arrays.sort(answers);
        String answer = "";
        for (int i = 0;i < answers.length;i++){
            answer += (char)answers[i].intValue();
            if (i != answers.length - 1){
                answer += "、";
            }
        }
        return answer;
    }

}
