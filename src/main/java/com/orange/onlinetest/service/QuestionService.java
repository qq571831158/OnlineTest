package com.orange.onlinetest.service;

import com.orange.onlinetest.dao.ClassDAO;
import com.orange.onlinetest.dao.QuestionDAO;
import com.orange.onlinetest.model.Question;
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
public class QuestionService {
    @Autowired
    private QuestionDAO questionDAO;


    public int addQuesion(String title,String content,String answer,int score,int teacherId,int type,String answerDesc){
        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setAnswer(answer);
        question.setScore(score);
        question.setTeacherId(teacherId);
        question.setType(type);
        question.setAnswerDesc(answerDesc);
        question.setCreateTime(new Date());
        question.setLastModify(new Date());
        return questionDAO.insertQuesion(question);
    }

    public List<Question> getAllQuestionByTeacherId(int teacherId){
        return questionDAO.selectByTeacherId(teacherId);
    }

    public Question getQuestionById(int questionId){
        return questionDAO.selectById(questionId);
    }
}
