package com.orange.onlinetest.service;

import com.orange.onlinetest.dao.StudentDAO;
import com.orange.onlinetest.dao.TicketDAO;
import com.orange.onlinetest.model.Student;
import com.orange.onlinetest.model.Ticket;
import com.orange.onlinetest.utils.OnlineTestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/8 9:40
 */
@Service
public class StudentService {
    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private TicketDAO ticketsDAO;

    /**
     * 用户登录方法
     * @param username  用户名
     * @param password  密码
     * @return map
     */
    public Map<String,Object> login(String username,String password){
        Map<String ,Object>map = new HashMap<>();
        if (StringUtils.isBlank(username)){
            map.put("msg","用户名不能为空");
            return map;
        }
        if (StringUtils.isBlank(password)){
            map.put("msg","密码不能为空");
        }
        Student student = studentDAO.selectByUsername(username);
        if (student == null){
            map.put("msg","用户名不存在");
            return map;
        }
        if (!OnlineTestUtil.MD5(password+student.getSalt()).equals(student.getPassword())){
            map.put("msg","密码错误");
            return map;
        }
        String ticket = addTicket(student.getId());
        map.put("userId",student.getId());
        map.put("ticket",ticket);
        return map;
    }

    /**
     * 增加ticket,根据用户的id生成一个唯一的ticket,随后放入cookie，作为用户标示。
     * @param userId  用户id
     * @return ticket
     */
    public String addTicket(int userId){
        Ticket ticket = new Ticket();
        ticket.setUserId(userId);
        ticket.setUserType(1);
        Date now = new Date();
        now.setTime(3600*24*100+now.getTime());
        ticket.setExpired(now);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
        ticketsDAO.addTicket(ticket);
        return ticket.getTicket();
    }

    /**
     * 用户注册
     * @param username   用户名
     * @param password   密码
     * @param name       姓名
     *  下次加入beanvalidation。
     * @return map
     */
    public Map<String,Object> regist(String username,String password,String name){
        Map<String,Object> map = new HashMap<>();
        if (StringUtils.isBlank(username)){
            map.put("msg","用户名不能为空");
            return map;
        }
        if (StringUtils.isBlank(password)){
            map.put("msg","密码不能为空");
            return map;
        }
        if (StringUtils.isBlank(name)){
            map.put("msg","姓名不能为空");
            return map;
        }
        Student s = studentDAO.selectByUsername(username);
        if (s != null) {
            map.put("msg", "用户名已存在");
        }
        Student student = new Student();
        student.setUsername(username);
        student.setPassword(password);
        student.setName(name);
        student.setSalt(UUID.randomUUID().toString().substring(0,5));
        Date now = new Date();
        now.setTime(now.getTime()+3600*1000*12);
        student.setLastModify(now);
        studentDAO.addUser(student);
        return map;

    }
}
