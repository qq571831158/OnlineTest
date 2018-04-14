package com.orange.onlinetest.service;

import com.orange.onlinetest.dao.QuestionTypeDAO;
import com.orange.onlinetest.model.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/19 8:59
 */
@Service
public class QuestionTypeService {

    @Autowired
    private QuestionTypeDAO questionTypeDAO;

    public void addQuestionType(QuestionType questionType){
        questionTypeDAO.addQuestionType(questionType);
    }

    public List<QuestionType> selectAllQuestionType(){
        return questionTypeDAO.selectAll();
    }

    public QuestionType selectById(int id){
        return questionTypeDAO.selectById(id);
    }


}
