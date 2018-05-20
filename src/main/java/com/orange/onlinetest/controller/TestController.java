package com.orange.onlinetest.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.orange.onlinetest.cache.AnswerCache;
import com.orange.onlinetest.cache.QuestionCache;
import com.orange.onlinetest.kafka.Message;
import com.orange.onlinetest.model.*;
import com.orange.onlinetest.responsedate.GetTestResponse;
import com.orange.onlinetest.service.AnswerService;
import com.orange.onlinetest.service.QuestionService;
import com.orange.onlinetest.service.TestService;
import com.orange.onlinetest.utils.OnlineTestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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

    private static final byte TYPE_NEXT_QUESTION = 1;//下一题

    private static final byte TYPE_NOW_QUESTION = 0;//标签

    @Autowired
    TestService testService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionCache questionCache;
    @Autowired
    private AnswerCache answerCache;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private KafkaTemplate kafkaTemplate;

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
                getTestResponse = getTestResponse(question,testId + "",1 + "",TYPE_NOW_QUESTION);
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
                getTestResponse = getTestResponse(question,testId,nowQuestionId,TYPE_NEXT_QUESTION);

            }else {
                Integer id = Integer.parseInt(nowQuestionId) - 1;
                question = questionCache.hGetQuestion(Integer.parseInt(testId),id);
                getTestResponse = getTestResponse(question,testId,id.toString(),TYPE_NEXT_QUESTION);
                model.addAttribute("msg","当前已是最后一题");
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
                getTestResponse = getTestResponse(question,testId,nowQuestionId,TYPE_NOW_QUESTION);
            }else {
                model.addAttribute("msg","当前题号不存在");
            }
            model.addAttribute("getTestResponse",getTestResponse);
            return "student/test";
        }else {
            model.addAttribute("msg","无此考试");
            return "student/home";
        }
    }

    @RequestMapping(value = "/setAnswer")
    @ResponseBody
    public String setAnawser(String testId,String qId,@RequestParam("answers[]")Integer[] answers){
        Integer stuId = hostHolder.getStus().getId();
        if (stuId == null || stuId == 0){
            return OnlineTestUtil.getJSONString(500,"用户验证错误");
        }
        if (testId == null || qId == null || answers.length == 0 || testId.equals("") || qId.equals("")){
            return OnlineTestUtil.getJSONString(400,"参数错误");
        }
        answerCache.hsetAnswer(testId,stuId.toString(),qId,OnlineTestUtil.getAnswers(answers));
        return OnlineTestUtil.getJSONString(200,"缓存答案成功");
    }

    @RequestMapping(value = "/setTxtAnswer")
    @ResponseBody
    public String setTxtAnswer(String testId,String qId,@RequestParam("answer")String answer){
        Integer stuId = hostHolder.getStus().getId();
        if (stuId == null || stuId == 0){
            return OnlineTestUtil.getJSONString(500,"用户验证错误");
        }
        if (testId == null || qId == null || testId.equals("") || qId.equals("")){
            return OnlineTestUtil.getJSONString(400,"参数错误");
        }
        if (answer==null || answer.equals("")){
            answer = "";
        }
        answerCache.hsetAnswer(testId,stuId.toString(),qId,answer);
        return OnlineTestUtil.getJSONString(200,"缓存答案成功");
    }

    @RequestMapping(value = "/submitAnswer")
    @ResponseBody
    public String submitAnswer(Integer testId){
        if (hostHolder.getStus() == null){
            return OnlineTestUtil.getJSONString(500,"用户验证错误");
        }
        try {
            Map<Integer,String> map = answerCache.hgetAllAnswer(testId,hostHolder.getStus().getId());
            Answer answer = answerService.addAnswer(hostHolder.getStus().getId(),testId,map);
            if (answer != null && answer.getId() > 0){
                pushToKafka(answer);
                return OnlineTestUtil.getJSONString(200,"提交成功");
            }else {
                return OnlineTestUtil.getJSONString(500,"提交失败，请重新提交");
            }

        }catch (Exception e){
            e.printStackTrace();
            return OnlineTestUtil.getJSONString(500,"服务器异常，请稍后重试");
        }
    }

    private GetTestResponse getTestResponse(Question question,String testId,String nowQuestionId,int type) {
        GetTestResponse getTestResponse = new GetTestResponse();
        getTestResponse.setTestId(Integer.parseInt(questionCache.hGetKey(Integer.parseInt(testId), QuestionCache.SUBKEY_TEST_ID)));
        getTestResponse.setTestPaperName(questionCache.hGetKey(Integer.parseInt(testId), QuestionCache.SUBKEY_TEST_PAPER_NAME));
        getTestResponse.setTestScore(Integer.parseInt(questionCache.hGetKey(Integer.parseInt(testId), QuestionCache.SUBKEY_TEST_SCORE)));
        getTestResponse.setQuestionTitle(question.getTitle());
        getTestResponse.setQuestionsLength(Integer.parseInt(questionCache.hGetKey(Integer.parseInt(testId), QuestionCache.SUBKEY_QUESTION_LENGTH)));
        getTestResponse.setQuestionId(question.getId());
        getTestResponse.setQuestionType(DataConvert.questionTypeInt2String(question.getType()));
        getTestResponse.setEndTime(Long.parseLong(questionCache.hGetKey(Integer.parseInt(testId),QuestionCache.SUBKEY_TEST_END_TIME)));
        String answer = "";
        if (type == TYPE_NEXT_QUESTION){
            getTestResponse.setNowQuestionNum(Integer.parseInt(nowQuestionId) + 1);
            answer = answerCache.hgetAnswer(Integer.parseInt(testId),hostHolder.getStus().getId(),Integer.parseInt(nowQuestionId) + 1);
        }else {
            getTestResponse.setNowQuestionNum(Integer.parseInt(nowQuestionId));
            answer = answerCache.hgetAnswer(Integer.parseInt(testId),hostHolder.getStus().getId(),Integer.parseInt(nowQuestionId));
        }
        if (question.getType() == Question.TYPE_SINGLE_SELECT || question.getType() == Question.TYPE_MUILT_SELECT || question.getType() == Question.TYPE_TRUE_FALSE) {
            Integer[] answers;
            if (answer != null && !answer.equals("")){
                String[] allAnswer = answer.split(";");
                answers = new Integer[allAnswer.length];
                for (int i = 0 ; i < answers.length ; i ++){
                    char singleAnswer = allAnswer[i].charAt(0);
                    answers[i] = singleAnswer - 65;
                }
                getTestResponse.setSelected(answers);
            }
            String[] selections = question.getContent().split(Question.SELECTION_SPLIT_STRING);
            getTestResponse.setSelections(selections);
        }else {
            getTestResponse.setAnswer(answer.trim());

        }
        getEndTime(getTestResponse);
        return getTestResponse;
    }

    private void getEndTime(GetTestResponse getTestResponse){
        long endTime = getTestResponse.getEndTime() - new Date().getTime();
        int hour = (int)endTime / 3600 / 1000;
        endTime = endTime % 3600000;
        int min =  (int)endTime / 60000;
        endTime = endTime % 60000;
        int second = (int) endTime/1000 ;
        if (hour < 10){
            getTestResponse.setHour("0"+hour);
        }else {
            getTestResponse.setHour(hour+"");
        }
        if (min < 10){
            getTestResponse.setMin("0" + min);
        }else {
            getTestResponse.setMin(min + "");
        }
        if (second < 10){
            getTestResponse.setSecond("0" + second);
        }else {
            getTestResponse.setSecond(second + "");
        }
    }

    private  void pushToKafka(Answer answer){
        try {
            Message message = new Message();
            message.setId((long)answer.getId());
            message.setMsg(new Gson().toJson(answer));
            message.setSendTime(new Date());
            kafkaTemplate.send("correctPaper",  new Gson().toJson(message));
            System.out.println("发送kafka成功.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送kafka失败" + e.getMessage());
        }
    }
}
