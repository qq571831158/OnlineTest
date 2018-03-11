package com.orange.onlinetest.model;

import java.util.Date;

/**
 * Created by apple on 2018/3/10.
 */
public class Course {
    private int id;

    private String courseName;

    private int teaId;

    private Date createTime;

    private int courseStuNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTeaId() {
        return teaId;
    }

    public void setTeaId(int teaId) {
        this.teaId = teaId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCourseStuNum() {
        return courseStuNum;
    }

    public void setCourseStuNum(int courseStuNum) {
        this.courseStuNum = courseStuNum;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", teaId=" + teaId +
                ", createTime=" + createTime +
                ", courseStuNum=" + courseStuNum +
                '}';
    }
}
