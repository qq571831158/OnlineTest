package com.orange.onlinetest.service;

import com.orange.onlinetest.model.Course;
import com.orange.onlinetest.model.TestPaper;
import com.orange.onlinetest.model.ViewObject;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by apple on 2018/3/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StucorServiceTest {
    @Autowired
    private StucorService stucorService;
    @Test
    public void getStuIndexDataModel() throws Exception {
        List<ViewObject> vos = stucorService.getStuIndexDataModel(1);
        for (ViewObject vo : vos){
            System.out.println(vo.get("course"));
            System.out.println(vo.get("tests"));
        }
    }

}