package com.orange.onlinetest.model;

import java.io.Serializable;

public class Test implements Serializable  {

    private int id;

    private int questionId;

    private int testpaperId;

    public Test(){}

    public Test(int id, int questionId, int testpaperId) {
        this.id = id;
        this.questionId = questionId;
        this.testpaperId = testpaperId;
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
        return testpaperId;
    }

    public void setTestpaperId(int testpaperId) {
        this.testpaperId = testpaperId;
    }
}
