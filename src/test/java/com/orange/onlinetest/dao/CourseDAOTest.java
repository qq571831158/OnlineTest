package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Course;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


/**
 * Created by apple on 2018/3/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseDAOTest {

    @Autowired
    private CourseDAO courseDAO;

    @Test
    public void selectById() throws Exception {
        System.out.println(courseDAO.selectById(1).getCourseName());
    }

    @Test
    public void selectByTeaId() throws Exception {
        List<Course>list = courseDAO.selectByTeaId(1);
        System.out.println(list.get(1).getCourseName());
    }

    @Test
    public void insertCourse() throws Exception {
        Course course = new Course();
        course.setCourseName("c++语言程序设计");
        course.setTeaId(1);
        course.setCreateTime(new Date());
        course.setCourseStuNum(30);
        courseDAO.insertCourse(course);
    }

    @Test
    public void deleteCourse() throws Exception {
        courseDAO.deleteCourse(1);
    }

}