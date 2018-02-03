package com.orange.onlinetest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class IndexController {
    @RequestMapping(path = {"/",})
    public String index(){
        return "login";
    }

    @RequestMapping(path = {"toTeacherIndex",})
    public String toTeacherIndex(){
        return "teacher/index";
    }

    @RequestMapping(path = {"toStudentIndex",})
    public String toStudentIndex(){
        return "student/index";
    }
    
}
