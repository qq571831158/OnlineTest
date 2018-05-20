package com.orange.onlinetest.responsedate;

import com.orange.onlinetest.model.Question;

public class CorrectResult extends Question {

    private Integer correctResultId;

    private byte isRight;

    public Integer getCorrectResultId() {
        return correctResultId;
    }

    public void setCorrectResultId(Integer correctResultId) {
        this.correctResultId = correctResultId;
    }

    public byte getIsRight() {
        return isRight;
    }

    public void setIsRight(byte isRight) {
        this.isRight = isRight;
    }

    public CorrectResult(){}

    public CorrectResult(Question question){
        this.setId(question.getId());
        this.setTitle(question.getTitle());
        this.setContent(question.getContent());
        this.setAnswerDesc(question.getAnswerDesc());
        this.setType(question.getType());
        this.setAnswer(question.getAnswer());
        this.setScore(question.getScore());
        this.setTeacherId(question.getTeacherId());
        this.setCreateTime(question.getCreateTime());
        this.setLastModify(question.getLastModify());
    }
}
