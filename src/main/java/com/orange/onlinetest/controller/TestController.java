package com.orange.onlinetest.controller;

import com.orange.onlinetest.cache.QuestionCache;
import com.orange.onlinetest.model.DataConvert;
import com.orange.onlinetest.model.Question;
import com.orange.onlinetest.model.Test;
import com.orange.onlinetest.responsedate.GetTestResponse;
import com.orange.onlinetest.service.QuestionService;
import com.orange.onlinetest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/10 18:21
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    TestService testService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionCache questionCache;

    @RequestMapping("/toTest")
    public String toTest(Model model,@RequestParam("testId") int testId){
        Test test = testService.getTestById(testId);
        GetTestResponse getTestResponse = new GetTestResponse();
        if (test != null){
            String[] questionIds = test.getQuestions().split(Test.QUESTION_SPLIT_STRING);
            if (questionIds.length != 0){
                Map<Integer,Question>questionsMap = new HashMap<>();
                for (int i = 0;i < questionIds.length; i++){
                    questionsMap.put(i + 1,questionService.getQuestionById(Integer.parseInt(questionIds[i])));
                }
                questionCache.hset(questionsMap,test);
                Question question = questionService.getQuestionById(Integer.parseInt(questionIds[0]));
                getTestResponse.setTestId(test.getId());
                getTestResponse.setTestPaperName(test.getTestPaperName());
                getTestResponse.setTestScore(test.getTestPaperScore());
                getTestResponse.setQuestionTitle(question.getTitle());
                getTestResponse.setQuestionsLength(questionIds.length);
                getTestResponse.setQuestionId(question.getId());
                getTestResponse.setQuestionType(DataConvert.questionTypeInt2String(question.getType()));
                getTestResponse.setNowQuestionNum(1);
                if (question.getType() == Question.TYPE_SINGLE_SELECT || question.getType() == Question.TYPE_MUILT_SELECT){
                    String[] selections = question.getContent().split(Question.SELECTION_SPLIT_STRING);
                    getTestResponse.setSelections(selections);
                }
            }
            model.addAttribute("getTestResponse",getTestResponse);
            return "student/test";
        }else {
            model.addAttribute("msg","无此考试");
            return "student/home";
        }
    }


    @RequestMapping(value = "/getNextQuestion",method = RequestMethod.GET)
    public String getNextQuestion(Model model,String testId,String nowQuestionId){
        if (!"".equals(testId) && !"".equals(nowQuestionId)){
            Question question = questionCache.hGetQuestion(Integer.parseInt(testId),Integer.parseInt(nowQuestionId)+1);
            GetTestResponse getTestResponse = new GetTestResponse();
            if (question != null){
                getTestResponse.setTestId(Integer.parseInt(questionCache.hGetKey(Integer.parseInt(testId),QuestionCache.SUBKEY_TEST_ID)));
                getTestResponse.setTestPaperName(questionCache.hGetKey(Integer.parseInt(testId),QuestionCache.SUBKEY_TEST_PAPER_NAME));
                getTestResponse.setTestScore(Integer.parseInt(questionCache.hGetKey(Integer.parseInt(testId),QuestionCache.SUBKEY_TEST_SCORE)));
                getTestResponse.setQuestionTitle(question.getTitle());
                getTestResponse.setQuestionsLength(Integer.parseInt(questionCache.hGetKey(Integer.parseInt(testId),QuestionCache.SUBKEY_QUESTION_LENGTH)));
                getTestResponse.setQuestionId(question.getId());
                getTestResponse.setQuestionType(DataConvert.questionTypeInt2String(question.getType()));
                getTestResponse.setNowQuestionNum(Integer.parseInt(nowQuestionId) + 1);
                if (question.getType() == Question.TYPE_SINGLE_SELECT || question.getType() == Question.TYPE_MUILT_SELECT){
                    String[] selections = question.getContent().split(Question.SELECTION_SPLIT_STRING);
                    getTestResponse.setSelections(selections);
                }
            }
            model.addAttribute("getTestResponse",getTestResponse);
            return "student/test";
        }else {
            model.addAttribute("msg","无此考试");
            return "student/home";
        }
    }

    @RequestMapping(value = "/getQuestionById",method = RequestMethod.GET)
    public String getQuestionById(Model model,String testId,String nowQuestionId){
        System.out.println(nowQuestionId);
        if (!"".equals(testId) && !"".equals(nowQuestionId)){
            Question question = questionCache.hGetQuestion(Integer.parseInt(testId),Integer.parseInt(nowQuestionId));
            GetTestResponse getTestResponse = new GetTestResponse();
            if (question != null){
                getTestResponse.setTestId(Integer.parseInt(questionCache.hGetKey(Integer.parseInt(testId),QuestionCache.SUBKEY_TEST_ID)));
                getTestResponse.setTestPaperName(questionCache.hGetKey(Integer.parseInt(testId),QuestionCache.SUBKEY_TEST_PAPER_NAME));
                getTestResponse.setTestScore(Integer.parseInt(questionCache.hGetKey(Integer.parseInt(testId),QuestionCache.SUBKEY_TEST_SCORE)));
                getTestResponse.setQuestionTitle(question.getTitle());
                getTestResponse.setQuestionsLength(Integer.parseInt(questionCache.hGetKey(Integer.parseInt(testId),QuestionCache.SUBKEY_QUESTION_LENGTH)));
                getTestResponse.setQuestionId(question.getId());
                getTestResponse.setQuestionType(DataConvert.questionTypeInt2String(question.getType()));
                getTestResponse.setNowQuestionNum(Integer.parseInt(nowQuestionId));
                if (question.getType() == Question.TYPE_SINGLE_SELECT || question.getType() == Question.TYPE_MUILT_SELECT){
                    String[] selections = question.getContent().split(Question.SELECTION_SPLIT_STRING);
                    getTestResponse.setSelections(selections);
                }
            }
            model.addAttribute("getTestResponse",getTestResponse);
            return "student/test";
        }else {
            model.addAttribute("msg","无此考试");
            return "student/home";
        }
    }

}
