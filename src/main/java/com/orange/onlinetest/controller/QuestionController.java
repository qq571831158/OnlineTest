package com.orange.onlinetest.controller;

import com.orange.onlinetest.service.QuestionService;
import com.orange.onlinetest.utils.OnlineTestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/8 15:44
 */
@Controller
@RequestMapping(value = "exam")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @RequestMapping(value = "addQuestion")
    @ResponseBody
    public String addQuestion(String title,String content,String answer,Integer score){
        try{
            if (questionService.addQuesion(title,content,answer,score,1)>0){
                return OnlineTestUtil.getJSONString(100,"提交成功");
            }else {
                return OnlineTestUtil.getJSONString(200,"失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return OnlineTestUtil.getJSONString(300,"发生异常");
        }



    }
}
