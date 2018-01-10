package com.orange.onlinetest.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TestPaper extends BaseModel implements Serializable {

    private String title;

    private String describution;

    private String content;

    private int lastTime;

    private Date expireTime;

    private int teacherId;

    private String result;

    public TestPaper(){}

    public TestPaper(int id, Date createTime, Date lastModify, String title, String describution, String content, int lastTime, Date expireTime, int teacherId, String result) {
        super(id, createTime, lastModify);
        this.title = title;
        this.describution = describution;
        this.content = content;
        this.lastTime = lastTime;
        this.expireTime = expireTime;
        this.teacherId = teacherId;
        this.result = result;
    }

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
}
