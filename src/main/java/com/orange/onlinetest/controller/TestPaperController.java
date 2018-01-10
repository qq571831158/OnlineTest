package com.orange.onlinetest.controller;

import com.orange.onlinetest.model.HostHolder;
import com.orange.onlinetest.model.TestPaper;
import com.orange.onlinetest.service.ClassService;
import com.orange.onlinetest.service.TestPaperService;
import com.orange.onlinetest.utils.OnlineTestUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/8 15:45
 */
@Controller
@RequestMapping(value = "/user")
public class TestPaperController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TestPaperController.class);

    @Autowired
    private TestPaperService testPaperService;
    @Autowired
    private ClassService classService;
    @Autowired
    private HostHolder hostHolder;

    @RequestMapping("toListAllTestPapers")
    public String listAllTestPapers(Model model){
        List<TestPaper> list = testPaperService.getAllTestPapersByTeacherId(hostHolder.getTeas().getId());
        model.addAttribute("testPapers",list);
        return "teacher/listAllTestPapers";
    }

    @RequestMapping("addTestPaper")
    @ResponseBody
    public String addTestPaper(@RequestParam("title")String title,
                               @RequestParam("content")String content,
                               @RequestParam("lastTime")int lastTime,
                               @RequestParam("expireTime")String expireTime){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(expireTime);
            boolean isSuccess = testPaperService.addTestPaper(title,content,lastTime,date,hostHolder.getTeas().getId());
            if (isSuccess){
                return OnlineTestUtil.getJSONString(200,"添加成功");
            }
            return OnlineTestUtil.getJSONString(500,"服务器内部错误");

        } catch (ParseException e) {
            logger.error("增加试卷日期转化失败" +e.getMessage());
            e.printStackTrace();
            return OnlineTestUtil.getJSONString(500,"日期转化失败");
        }
    }

    @RequestMapping(value = "toAddTestPaper")
    public String toAddTestPaper(){
        return "teacher/addTestPaper";
    }

    @RequestMapping("toListStudentAllTestPapers")
    public String toListStudentAllTestPapers(Model model){
        int  classId = hostHolder.getStus().getClassId();
        List<TestPaper> testPapers = testPaperService.getAllTestPapersByTeacherId(classService.getClassById(classId).getTeacherId());
        model.addAttribute("testPapers",testPapers);
        return "student/listAllTestPapers";
    }

}
