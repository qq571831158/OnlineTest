package com.orange.onlinetest.model;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/10 17:37
 */
public class AllQuesionsViewModel {
    private String title;

    private String content;

    private String answer;

    private int score;

    private String testPaperName;

    public AllQuesionsViewModel(){}

    public AllQuesionsViewModel(String title, String content, String answer, int score, String testPaperName) {
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.score = score;
        this.testPaperName = testPaperName;
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

    public String getTestPaperName() {
        return testPaperName;
    }

    public void setTestPaperName(String testPaperName) {
        this.testPaperName = testPaperName;
    }
}
