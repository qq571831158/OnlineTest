package com.orange.onlinetest.model;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/17 13:41
 */
@Component
public class QuestionHolder {
    ThreadLocal<LinkedList<Question>>linkedListThreadLocal = new ThreadLocal<>();

    public LinkedList<Question> getLinkedList(){
        return linkedListThreadLocal.get();
    }

    public void setLinkedListThreadLocal(LinkedList<Question> questions){
        linkedListThreadLocal .set(questions);
    }
}
