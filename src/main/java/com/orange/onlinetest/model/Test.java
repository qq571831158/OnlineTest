package com.orange.onlinetest.model;

import java.io.Serializable;
import java.util.Date;

public class Test implements Serializable  {

    public static final String QUESTION_SPLIT_STRING = ";";


    private int id;

    private int testPaperId;

    private String testPaperName;

    private int testPaperScore;

    private int courseId;

    private String questions;

    private Date startTime;



    public Test(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestPaperName() {
        return testPaperName;
    }

    public void setTestPaperName(String testPaperName) {
        this.testPaperName = testPaperName;
    }

    public int getTestPaperScore() {
        return testPaperScore;
    }

    public void setTestPaperScore(int testPaperScore) {
        this.testPaperScore = testPaperScore;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
