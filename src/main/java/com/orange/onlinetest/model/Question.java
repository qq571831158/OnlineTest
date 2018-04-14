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

    private int questionType;

    public Question(){}

    public Question(int id, Date createTime, Date lastModify, String title, String content, String answer, int score, int teacherId,int questionType) {
        super(id, createTime, lastModify);
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.score = score;
        this.teacherId = teacherId;
        this.questionType = questionType;
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

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }
}
