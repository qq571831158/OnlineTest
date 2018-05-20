package com.orange.onlinetest.service;

import com.orange.onlinetest.cache.QuestionCache;
import com.orange.onlinetest.dao.AnswerDAO;
import com.orange.onlinetest.model.Answer;
import com.orange.onlinetest.model.Question;
import com.orange.onlinetest.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnswerService {
    @Autowired
    private AnswerDAO answerDAO;
    @Autowired
    private TestService testService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionCache questionCache;
    public Answer addAnswer(Integer stuId,int testId,Map<Integer,String> answers){
        String answerResult = "";
        int length = Integer.parseInt(questionCache.hGetKey(testId,QuestionCache.SUBKEY_QUESTION_LENGTH));
        for (int i = 1; i <= length;i++){
            answerResult += i;
            answerResult += ":";
            if (answers.get(i)!= null && !answers.get(i).equals("")){
                answerResult += answers.get(i);
                answerResult += ";";
            }else {
                answerResult += "null";
                answerResult += ";";
            }
        }

        //1:A;2:B;3:C;

        if (!answerResult.equals("")){
            answerResult =  answerResult.substring(0, answerResult.length() - 1);
        }
        Answer answer = new Answer();
        answer.setStudentId(stuId);
        answer.setTestId(testId);
        answer.setAnswers(answerResult);
        answer.setCreateTime(new Date());
        answer.setLastModify(new Date());
        if (answerDAO.insertAnswer(answer) > 0){
            return answer;
        }
        return null;
    }

    public List<Question> getRigthAnswer(Integer testId){
        List<Question> list = new ArrayList<>();
        Test test = testService.getTestById(testId);
        if (test != null && !test.getQuestions().equals("")){
            String[] qids = test.getQuestions().split(";");
            for (String qid : qids){
                Question question = questionService.getQuestionById(Integer.parseInt(qid));
                list.add(question);
            }
        }
        return list;
    }
}
