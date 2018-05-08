package com.orange.onlinetest.responsedate;

/**
 * Created by apple on 2018/4/17.
 */
public class GetTestResponse {


    private int testId;

    private String testPaperName;

    private int questionId;

    private String questionType;

    private String questionTitle;

    private int testScore;

    private String[] selections;

    private int questionsLength;

    private int nowQuestionNum;

    public GetTestResponse(){}

    public GetTestResponse(int testId, String testPaperName, int questionId, String questionType, String questionTitle, int testScore, String[] selections,int questionsLength,int nowQuestionNum) {
        this.testId = testId;
        this.testPaperName = testPaperName;
        this.questionId = questionId;
        this.questionType = questionType;
        this.questionTitle = questionTitle;
        this.testScore = testScore;
        this.selections = selections;
        this.questionsLength = questionsLength;
        this.nowQuestionNum = nowQuestionNum;
    }


    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTestPaperName() {
        return testPaperName;
    }

    public void setTestPaperName(String testPaperName) {
        this.testPaperName = testPaperName;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public int getTestScore() {
        return testScore;
    }

    public void setTestScore(int testScore) {
        this.testScore = testScore;
    }

    public String[] getSelections() {
        return selections;
    }

    public void setSelections(String[] selections) {
        this.selections = selections;
    }

    public int getQuestionsLength() {
        return questionsLength;
    }

    public void setQuestionsLength(int questionsLength) {
        this.questionsLength = questionsLength;
    }

    public int getNowQuestionNum() {
        return nowQuestionNum;
    }

    public void setNowQuestionNum(int nowQuestionNum) {
        this.nowQuestionNum = nowQuestionNum;
    }
}
