package com.orange.onlinetest.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by apple on 2018/4/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDAOTest {

    @Autowired
    private TestDAO testDAO;
    @Test
    public void addTest() throws Exception {
    }

    @Test
    public void selectById() throws Exception {
        com.orange.onlinetest.model.Test test = testDAO.selectById(1);
        System.out.println(test.getQuestions());
    }

}