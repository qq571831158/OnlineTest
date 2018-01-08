package com.orange.onlinetest.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Question extends BaseModel implements Serializable{

    private String title;

    private String content;

    private String answer;

    private int score;

    private int teacherId;

    public Question(){}

    public Question(int id, Date createTime, Date lastModify, String title, String content, String answer, int score, int teacherId) {
        super(id, createTime, lastModify);
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.score = score;
        this.teacherId = teacherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
