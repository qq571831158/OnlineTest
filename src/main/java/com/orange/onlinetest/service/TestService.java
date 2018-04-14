package com.orange.onlinetest.service;

import com.orange.onlinetest.dao.QuestionDAO;
import com.orange.onlinetest.dao.TestDAO;
import com.orange.onlinetest.model.Question;
import com.orange.onlinetest.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/10 17:17
 */
@Service
public class TestService {
    @Autowired
    private TestDAO testDAO;

    @Autowired
    private QuestionDAO questionDAO;

    public LinkedList<Question> getOneTest(int testPaperId){
        List<Test> list =  testDAO.selectByTestPaperId(testPaperId);
        LinkedList<Question> questionList = new LinkedList<>();
        for (Test test : list){
            questionList.add(questionDAO.selectById(test.getQuestionId()));
        }
        return questionList;
    }

    public int addTest(int questionId,int testPaperId){
        Test test = new Test();
        test.setQuestionId(questionId);
        test.setTestpaperId(testPaperId);
        return testDAO.addTest(test);
    }
}
