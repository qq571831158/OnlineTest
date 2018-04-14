package com.orange.onlinetest.controller;

import com.alibaba.fastjson.JSONObject;
import com.orange.onlinetest.model.HostHolder;
import com.orange.onlinetest.model.Question;
import com.orange.onlinetest.model.QuestionHolder;
import com.orange.onlinetest.service.QuestionService;
import com.orange.onlinetest.service.TestService;
import com.orange.onlinetest.utils.JedisAdapter;
import com.orange.onlinetest.utils.RedisKey;
import org.apache.catalina.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/10 18:21
 */
@Controller
@RequestMapping("test")
public class TestController {
    @Autowired
    TestService testService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private JedisAdapter jedisAdapter;

    @Autowired
    private HostHolder hostHolder;

    LinkedList<Question>linkedList = new LinkedList<>();

    @RequestMapping("toTest")
    public String toTest( Model model, int testPaperId){
        System.out.println(testService.getOneTest(testPaperId).toString());
        linkedList = testService.getOneTest(testPaperId);

//        String redisKey = RedisKey.getTestKey(hostHolder.getStus().getId(),testPaperId);
//        for (int i = 0;i<list.size();i++){
//            jedisAdapter.set(redisKey,JSONObject.toJSONString(list.get(i)));
//        }
        model.addAttribute("question",linkedList.pollFirst());
        model.addAttribute("nextQuestionId",linkedList.getFirst().getId());
        return "student/test";
    }

    @RequestMapping("next")
    public String nextQuestion(Model model){
        model.addAttribute("question",linkedList.pollFirst());
        model.addAttribute("nextQuestionId",linkedList.getFirst().getId());
        return "student/test";
    }



}
