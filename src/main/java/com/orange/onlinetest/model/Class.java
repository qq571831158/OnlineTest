package com.orange.onlinetest.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Class extends BaseModel implements Serializable{

    private int studentNum;

    private String name;

    private int status;

    private int teacherId;

    public Class(){}

    public Class(int id, Date createTime, Date lastModify, int studentNum, String name, int status, int teacherId) {
        super(id, createTime, lastModify);
        this.studentNum = studentNum;
        this.name = name;
        this.status = status;
        this.teacherId = teacherId;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
