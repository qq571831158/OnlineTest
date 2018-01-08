package com.orange.onlinetest.model;

import org.springframework.stereotype.Component;

@Component
public class HostHolder {

    private static ThreadLocal<Student> stus = new ThreadLocal<>();

    private static ThreadLocal<Teacher> teas = new ThreadLocal<>();

    public void setStus(Student student){
        stus.set(student);
    }

    public Student getStus(){
        return stus.get();
    }

    public void clearStu(){
        stus.remove();
    }


    public  Teacher getTeas() {
        return teas.get();
    }

    public  void setTeas(Teacher teacher) {
        teas.set(teacher);
    }

    public void clearTea(){
        teas.remove();
    }
}
