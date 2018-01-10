package com.orange.onlinetest.controller;

import com.orange.onlinetest.model.Student;
import com.orange.onlinetest.model.Teacher;
import com.orange.onlinetest.service.StudentService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/8 10:00
 */
@Controller
@RequestMapping(value = "student")
public class LoginController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private StudentService studentService;


    /**
     * 用户登录
     * @param model
     * @param username
     * @param password
     * @param imageCheck
     * @param session
     * @param response
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(Model model, String username, String password, String imageCheck,String identity, HttpSession session, HttpServletResponse response){
        String imageCode = session.getAttribute("imageCode").toString();
        int id = Integer.parseInt(identity);
        try{
            Map<String,Object> map =  studentService.login(username,password,id);
            if (!imageCheck.toUpperCase().equals(imageCode)){
                model.addAttribute("msg","验证码错误");
                return "login";
            }

            if (map.containsKey("ticket")){
                Cookie cookie = new Cookie("ticket",map.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                if (id == 1) {
                    return "redirect:/toStudentIndex";
                }
                return"redirect:/toTeacherIndex";
            }else {
                model.addAttribute("msg",map.get("msg"));
                return "login";
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("登录异常"+e.getMessage());
            return "login";
        }
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(value = "toRegisterPage")
    public String toRegistPage(){
        return "register";
    }

    /**
     * 注册入口
     * @param model
     * @param username
     * @param password
     * @param name
     * @return
     */
    @RequestMapping(value = "register")
    public String regist(Model model,String username,String password,String name){
        try {
            Map<String,Object> map = studentService.regist(username,password,name);
            if (!map.containsKey("msg")){
                return "login";
            }else {
                model.addAttribute("msg",map.get("msg"));
                return "register";
            }
        }catch (Exception e){
            model.addAttribute("msg","注册异常，请稍后再试");
            e.printStackTrace();
            logger.error("注册异常"+e.getMessage());
            return "register";
        }
    }


}
