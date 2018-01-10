package com.orange.onlinetest.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Teacher extends BaseModel implements Serializable {

    private String username;

    private String password;

    private String salt;
    private String name;

    private String phoneNum;

    private String email;

    private String activeCode;

    private int status;

    public Teacher(){}

    public Teacher(int id, Date createTime, Date lastModify, String username, String password,String salt, String name, String phoneNum, String email, String activeCode, int status) {
        super(id, createTime, lastModify);
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.activeCode = activeCode;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
