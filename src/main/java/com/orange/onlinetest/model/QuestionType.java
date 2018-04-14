package com.orange.onlinetest.model;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/19 8:50
 */
public class QuestionType {
    private int id;

    private String name;

    public QuestionType(){}

    public QuestionType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
