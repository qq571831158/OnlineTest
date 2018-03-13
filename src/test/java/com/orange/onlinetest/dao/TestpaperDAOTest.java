package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.TestPaper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/8 14:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestpaperDAOTest {
    @Autowired
    private TestPaperDAO testpaperDAO;

    @Test
    public void selectById() {
        TestPaper testpaper = testpaperDAO.selectById(15);
        System.out.println(testpaper.getStatus());
    }

    @Test
    public void addTestpaper() {
        TestPaper testpaper = new TestPaper();
        testpaper.setTitle("上海市第一次全国中学生考试");
        testpaper.setContent("奥术大师多萨达撒大所大声道阿萨德阿斯顿撒大四");
        testpaper.setDescribution("实打实大大");
        testpaper.setExpireTime(new Date());
        testpaper.setLastTime(120);
        testpaper.setResult("11321");
        testpaper.setTeacherId(1);
        testpaper.setCreateTime(new Date());
        testpaper.setLastModify(new Date());
        testpaperDAO.addTestpaper(testpaper);
    }

    @Test
    public void selectByTeacherId(){
        List<TestPaper> testpaper = testpaperDAO.selectByTeacherId(1);
        System.out.println(testpaper.get(0).getTitle());
    }
}