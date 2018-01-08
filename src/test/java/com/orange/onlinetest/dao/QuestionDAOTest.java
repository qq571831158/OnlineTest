package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/8 15:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionDAOTest {
    @Autowired
    private QuestionDAO questionDAO;

    @Test
    public void selectById() {
        Question question =questionDAO.selectById(3);
        System.out.println(question.getTitle());
    }

    @Test
    public void selectByTeacherId() {
        List<Question> questions = questionDAO.selectByTeacherId(1);
        System.out.println(questions.get(0).getTitle());
    }

    @Test
    public void insertQuesion() {
        Question question = new Question();
        question.setTitle("关于抽象类的叙述正确的是？( )");
        question.setContent("A.抽象类不可以实例化;B.抽象类就是一种特殊的接口;C.抽象类的方法都是抽象方法;D.抽象类的导出类一定不是抽象类");
        question.setAnswer("A");
        question.setScore(5);
        question.setTeacherId(1);
        question.setCreateTime(new Date());
        question.setLastModify(new Date());
        questionDAO.insertQuesion(question);
    }

    @Test
    public void deleteQuestion() {
        questionDAO.deleteQuestion(4);
    }
}