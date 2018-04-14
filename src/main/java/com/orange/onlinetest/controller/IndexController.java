package com.orange.onlinetest.controller;

import com.orange.onlinetest.model.HostHolder;
import com.orange.onlinetest.model.ViewObject;
import com.orange.onlinetest.service.StucorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {


    @Autowired
    private StucorService stucorService;
    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = {"/",})
    public String index(){
        return "login";
    }

    @RequestMapping(path = {"/toTeacherIndex",})
    public String toTeacherIndex(){
        return "teacher/home";
    }

    @RequestMapping(path = {"/toStudentIndex",})
    public ModelAndView toStudentIndex(){
        List<ViewObject> vos = stucorService.getStuIndexDataModel(hostHolder.getStus().getId());
        ModelAndView mv = new ModelAndView("student/home");
        mv.addObject("vos",vos);
        
        return mv;
    }
}
