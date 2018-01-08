package com.orange.onlinetest.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Answer extends BaseModel implements Serializable{

    private int studentId;

    private int questionId;

    private int testpaperId;

    private String answer;

    public Answer(){}

    public Answer(int id, Date createTime, Date lastModify, int studentId, int questionId, int testpaperId, String answer) {
        super(id, createTime, lastModify);
        this.studentId = studentId;
        this.questionId = questionId;
        this.testpaperId = testpaperId;
        this.answer = answer;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getTestpaperId() {
        return testpaperId;
    }

    public void setTestpaperId(int testpaperId) {
        this.testpaperId = testpaperId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
