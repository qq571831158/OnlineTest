package com.orange.onlinetest.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Student extends BaseModel implements Serializable{

    private String username;

    private String password;

    private String salt;

    private String name;

    private int age;

    private int sex;

    private int status;

    private int classId;

    public Student(){}

    public Student(int id, Date createTime, Date lastModify, String username, String password, String salt, String name, int age, int sex, int status, int classId) {
        super(id, createTime, lastModify);
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.status = status;
        this.classId = classId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
