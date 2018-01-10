package com.orange.onlinetest.controller;

import com.orange.onlinetest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/10 18:21
 */
@Controller
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping("toTest")
    public String toTest(Model model){

        return "student/test";
    }

}
