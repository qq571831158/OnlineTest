package com.orange.onlinetest.service;

import com.orange.onlinetest.model.QuestionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/19 9:03
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionTypeServiceTest {
    @Autowired
    private QuestionTypeService questionTypeService;

    @Test
    public void addQuestionType() {
    }

    @Test
    public void selectAllQuestionType() {
        List<QuestionType> list = questionTypeService.selectAllQuestionType();
        for (QuestionType questionType:list){
            System.out.println(questionType.getId()+"     "+questionType.getName());
        }
    }

    @Test
    public void selectById() {
    }
}