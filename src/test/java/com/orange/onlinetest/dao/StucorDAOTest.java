package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Stucor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by apple on 2018/3/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StucorDAOTest {
    @Autowired
    private StucorDAO stucorDAO;
    @Test
    public void selectById() throws Exception {
        System.out.println(stucorDAO.selectById(1));
    }

    @Test
    public void insertStucor() throws Exception {
        Stucor stucor = new Stucor();
        stucor.setCourseId(1);
        stucor.setStudentId(2014001);
        stucor.setCreateTime(new Date());
        stucor.setGrade(95);
        stucor.setTeaId(1);
        stucor.setTeaId(1);
        stucorDAO.insertStucor(stucor);
    }

    @Test
    public void deleteStucor() throws Exception {
        stucorDAO.deleteStucor(1);
    }

}