package com.orange.onlinetest.controller;

import com.google.gson.Gson;
import com.orange.onlinetest.dao.TestPaperDAO;
import com.orange.onlinetest.model.HostHolder;
import com.orange.onlinetest.model.Question;
import com.orange.onlinetest.model.TestPaper;
import com.orange.onlinetest.service.QuestionService;
import com.orange.onlinetest.service.TestPaperService;
import com.orange.onlinetest.utils.OnlineTestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/8 15:44
 */
@Controller
@RequestMapping(value = "user")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private TestPaperService testPaperService;

    @Autowired
    private HostHolder hostHolder;
    @RequestMapping(value = "addQuestion")
    @ResponseBody
    public String addQuestion(String title, @RequestParam("contents[]") String[] contents, Integer type, @RequestParam("answers[]") Integer[] answers, Integer score, @RequestParam("answerDesc") String answerDesc){
        try{
            if (title.equals("")|| type == null || score == null){
                return OnlineTestUtil.getJSONString(400,"参数错误");
            }
            if (questionService.addQuesion(title,OnlineTestUtil.getContents(contents),OnlineTestUtil.getAnswers(answers),score,hostHolder.getTeas().getId(),type,answerDesc)>0){
                return OnlineTestUtil.getJSONString(200,"提交成功");
            }else {
                return OnlineTestUtil.getJSONString(300,"失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return OnlineTestUtil.getJSONString(500,"发生异常");
        }
    }

    @RequestMapping(value = "toAddQuestionPage")
    public String toAddQuestionPage(Model model){
        List<TestPaper> testPaperList = testPaperService.getAllTestPapersByTeacherId(hostHolder.getTeas().getId());
        model.addAttribute("testPapers",testPaperList);
        return "teacher/addQuestion2";
    }

    @RequestMapping("toListAllQuestion")
    public String toListAllQuestion(Model model){
        List<Question>questions = questionService.getAllQuestionByTeacherId(hostHolder.getTeas().getId());
        model.addAttribute("questions",questions);
        return "teacher/listAllQuestions" ;
    }

    @RequestMapping(value = "/getAllQuestionsByTeaId")
    @ResponseBody
    public String getAllQuestionsByTeaId(){
        Integer teaId = hostHolder.getTeas().getId();
        if (teaId == null){
            return OnlineTestUtil.getJSONString(400,"用户验证错误");
        }
        List<Question> list = questionService.getAllQuestionByTeacherId(teaId);
        return OnlineTestUtil.getJSONString(200,new Gson().toJson(list));
    }




}
