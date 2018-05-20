package com.orange.onlinetest.model;

import java.io.Serializable;
import java.util.Date;

public class Question extends BaseModel implements Serializable{

    public static final String SELECTION_SPLIT_STRING = ";heng;";

    public static final int TYPE_SINGLE_SELECT = 1;

    public static final int TYPE_MUILT_SELECT = 2;

    public static final int TYPE_TRUE_FALSE = 3;

    public static final int TYPE_WENDA = 4;

    public static final int TYPE_ZHONGHE = 5;

    public static final int TYPE_OTHER = 6;

    private static final long serialVersionUID = -1049820059345702754L;

    private String title;

    private String content;

    private String answerDesc;

    private int type;

    private String answer;

    private int score;

    private int teacherId;

    public Question(){}


    public Question(int id, Date createTime, Date lastModify, String title, String content, String answerDesc, int type, String answer, int score, int teacherId) {
        super(id, createTime, lastModify);
        this.title = title;
        this.content = content;
        this.type = type;
        this.answer = answer;
        this.score = score;
        this.teacherId = teacherId;
        this.answerDesc = answerDesc;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAnswerDesc() {
        return answerDesc;
    }

    public void setAnswerDesc(String answerDesc) {
        this.answerDesc = answerDesc;
    }
}
