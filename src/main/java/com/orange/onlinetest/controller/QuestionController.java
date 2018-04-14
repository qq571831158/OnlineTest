package com.orange.onlinetest.controller;

import com.orange.onlinetest.dao.TestPaperDAO;
import com.orange.onlinetest.model.HostHolder;
import com.orange.onlinetest.model.Question;
import com.orange.onlinetest.model.TestPaper;
import com.orange.onlinetest.service.QuestionService;
import com.orange.onlinetest.service.QuestionTypeService;
import com.orange.onlinetest.service.TestPaperService;
import com.orange.onlinetest.service.TestService;
import com.orange.onlinetest.utils.OnlineTestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Autowired
    private QuestionTypeService questionTypeService;

    @Autowired
    private TestService testService;


    @RequestMapping(value = "addQuestion")
    @ResponseBody
    public String addQuestion(String questionTypeId,String  testPaperId,String title,String content,String answer,Integer score){
        try{
            int questionId = questionService.addQuesion(title,content,answer,score,Integer.parseInt(questionTypeId),hostHolder.getTeas().getId());
            if (questionId>0){
                if (testService.addTest(questionId,Integer.parseInt(testPaperId))>0){
                    return OnlineTestUtil.getJSONString(100,"提交成功");
                }else {
                    return OnlineTestUtil.getJSONString(200,"失败");
                }
            }else {
                return OnlineTestUtil.getJSONString(200,"失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return OnlineTestUtil.getJSONString(300,"发生异常");
        }
    }

    @RequestMapping(value = "toAddQuestionPage")
    public String toAddQuestionPage(Model model){
        List<TestPaper> testPaperList = testPaperService.getAllTestPapersByTeacherId(hostHolder.getTeas().getId());
        model.addAttribute("testPapers",testPaperList);
        model.addAttribute("questionTypes",questionTypeService.selectAllQuestionType());
        return "teacher/addQuestion";
    }

    @RequestMapping("toListAllQuestion")
    public String toListAllQuestion(Model model){
        List<Question>questions = questionService.getAllQuestion(hostHolder.getTeas().getId());
        model.addAttribute("questions",questions);
        return "teacher/listAllQuestions" ;
    }


}
