package com.orange.onlinetest.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Score extends BaseModel implements Serializable {

    private int studentId;

    private int testpaperId;

    private int score;

    public Score(){}

    public Score(int id, Date createTime, Date lastModify, int studentId, int testpaperId, int score) {
        super(id, createTime, lastModify);
        this.studentId = studentId;
        this.testpaperId = testpaperId;
        this.score = score;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTestpaperId() {
        return testpaperId;
    }

    public void setTestpaperId(int testpaperId) {
        this.testpaperId = testpaperId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
