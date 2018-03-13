package com.orange.onlinetest.service;

import com.orange.onlinetest.dao.CourseDAO;
import com.orange.onlinetest.dao.StucorDAO;
import com.orange.onlinetest.dao.TestPaperDAO;
import com.orange.onlinetest.model.Course;
import com.orange.onlinetest.model.Stucor;

import java.util.ArrayList;
import java.util.List;

import com.orange.onlinetest.model.TestPaper;
import com.orange.onlinetest.model.ViewObject;
import com.orange.onlinetest.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by apple on 2018/3/11.
 */
@Service
public class StucorService {
    @Autowired
    private StucorDAO stucorDAO;
    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private TestPaperDAO testPaperDAO;

    public List<ViewObject> getStuIndexDataModel(int stuId){
        List<ViewObject> vos = new ArrayList<>();
        List<Stucor>list =  stucorDAO.selectByStuId(stuId);
        for (Stucor stucor : list){
            ViewObject vo = new ViewObject();
            Course course = courseDAO.selectById(stucor.getCourseId());
            vo.set("course",course);
            List<TestPaper> list1 = testPaperDAO.selectAllTestPaperByCourseId(course.getId());
            if (list1.size() == 0){
                vo.set("msg","暂无考试信息");
            }else {
                for (TestPaper testPaper : list1){
                    testPaper.setTestStartTime(DateTimeUtil.mysqlDate2ViewDate(testPaper.getStartTime()));
                }
                vo.set("tests",list1);
            }
            vo.set("indexChar",course.getCourseName().substring(0,1));
            vos.add(vo);
        }
        return vos;
    }

}
