package com.orange.onlinetest.service;

import com.orange.onlinetest.controller.LoginController;
import com.orange.onlinetest.dao.TestPaperDAO;
import com.orange.onlinetest.model.TestPaper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/8 15:46
 */
@Service
public class TestPaperService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TestPaperService.class);

    @Autowired
    private TestPaperDAO testPaperDAO;

    public List<TestPaper>getAllTestPapersByTeacherId(int teacherId){
        return testPaperDAO.selectByTeacherId(teacherId);
    }

    public boolean addTestPaper(String title,String content,int lastTime,Date expireTime,int teacherId){
        TestPaper testPaper = new TestPaper();
        testPaper.setTitle(title);
        testPaper.setContent(content);
        testPaper.setLastTime(lastTime);
        testPaper.setExpireTime(expireTime);
        testPaper.setTeacherId(teacherId);
        testPaper.setCreateTime(new Date());
        testPaper.setLastModify(new Date());
        try {
            testPaperDAO.addTestpaper(testPaper);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("增加试卷失败"+e.getMessage());
            return false;
        }
    }

}
