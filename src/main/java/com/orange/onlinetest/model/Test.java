package com.orange.onlinetest.model;

import java.io.Serializable;

public class Test implements Serializable  {

    private int id;

    private int questionId;

    private int testPaperId;

    public Test(){}

    public Test(int id, int questionId, int testPaperId) {
        this.id = id;
        this.questionId = questionId;
        this.testPaperId = testPaperId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getTestpaperId() {
        return testPaperId;
    }

    public void setTestpaperId(int testPaperId) {
        this.testPaperId = testPaperId;
    }
}
