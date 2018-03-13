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
        List<Course>list = courseDAO.selectByTeaId(2);
        for (Course c : list){
            System.out.println(c.getCreateTime());
            System.out.println(c.getCourseName());
        }
    }

    @Test
    public void insertCourse() throws Exception {
        Course course = new Course();
        course.setCourseName("操作系统");
        course.setTeaId(2);
        course.setCreateTime(new Date());
        course.setCourseStuNum(30);
        Course c1 = course;
        c1.setCourseName("数据库系统概论");
        Course c2 = course;
        c2.setCourseName("计算机网络");
        courseDAO.insertCourse(course);
        courseDAO.insertCourse(c1);
        courseDAO.insertCourse(c2);
    }

    @Test
    public void deleteCourse() throws Exception {
        courseDAO.deleteCourse(1);
    }

}