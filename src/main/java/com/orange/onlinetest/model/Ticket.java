package com.orange.onlinetest.model;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/8 9:22
 */
public class Ticket {

    private int id;

    private int userId;

    private String ticket;

    private Date expired;

    private int status;

    private int userType;

    public Ticket(){

    }

    public Ticket(int id, int userId, String ticket, Date expired, int status, int userType) {
        this.id = id;
        this.userId = userId;
        this.ticket = ticket;
        this.expired = expired;
        this.status = status;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
