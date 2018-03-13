package com.orange.onlinetest.model;

import java.io.Serializable;

public class Test implements Serializable  {

    private int id;

    private int questionId;

    private int testPaperId;

    private String questions;

    public Test(){}


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


    public int getTestPaperId() {
        return testPaperId;
    }

    public void setTestPaperId(int testPaperId) {
        this.testPaperId = testPaperId;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }
}
