package com.orange.onlinetest.model;

import org.springframework.data.redis.core.index.PathBasedRedisIndexDefinition;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TestPaper extends BaseModel implements Serializable {

    private static final Byte STATUS_END = 1;//考试结束

    private static final Byte STATUS_TESTING = 0;//考试进行中

    private static final Byte STATUS_NO_START = -1;//考试尚未开始

    private String title;

    private String describution;

    private String content;

    private int lastTime;

    private Date expireTime;

    private int teacherId;

    private String result;

    private String questions;

    private int courseId;

    private Date startTime;

    private String testStartTime;

    private Byte status;
    public TestPaper(){}


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribution() {
        return describution;
    }

    public void setDescribution(String describution) {
        this.describution = describution;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getTestStartTime() {
        return testStartTime;
    }

    public void setTestStartTime(String testStartTime) {
        this.testStartTime = testStartTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
