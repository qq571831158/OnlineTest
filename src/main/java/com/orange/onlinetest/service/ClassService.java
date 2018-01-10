package com.orange.onlinetest.service;

import com.orange.onlinetest.dao.ClassDAO;
import com.orange.onlinetest.model.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/10 18:17
 */
@Service
public class ClassService {
    @Autowired
    private ClassDAO classDAO;

    public Class getClassById(int id){
        return classDAO.selectById(id);
    }
}
