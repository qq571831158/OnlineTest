package com.orange.onlinetest.interceptor;

import com.orange.onlinetest.dao.StudentDAO;
import com.orange.onlinetest.dao.TeacherDAO;
import com.orange.onlinetest.dao.TicketDAO;
import com.orange.onlinetest.model.HostHolder;
import com.orange.onlinetest.model.Student;
import com.orange.onlinetest.model.Teacher;
import com.orange.onlinetest.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginIntecepter implements HandlerInterceptor{
    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private TeacherDAO teacherDAO;

    int identity;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("ticket")){
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
        if (ticket!=null){
            Ticket ticket1 = ticketDAO.selectByTicket(ticket);
            if (ticket1 == null || ticket1.getExpired().before(new Date())||ticket1.getStatus()==1){
                return true;
            }
            if (ticket1.getUserType()==1){
                identity = 1;
                Student student = studentDAO.selectById(ticket1.getUserId());
                hostHolder.setStus(student);
            }else {
                identity = 2;
                Teacher teacher = teacherDAO.selectById(ticket1.getUserId());
                hostHolder.setTeas(teacher);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (modelAndView!=null){
            if (identity != 0){
                if (identity == 1){
                    modelAndView.addObject("user",hostHolder.getStus());
                }else {
                    modelAndView.addObject("user",hostHolder.getTeas());
                }
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        if (identity != 0){
            if (identity == 1){
                hostHolder.clearStu();
            }else {
                hostHolder.clearTea();
            }
        }
    }
}
