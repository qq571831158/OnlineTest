package com.orange.onlinetest.model;

import java.sql.Timestamp;
import java.util.Date;

public class BaseModel {
    //主键唯一标示
    private int id;
    //创建时间标示
    private Date createTime;
    //最后修改时间标示
    private Date lastModify;

    public BaseModel(){super();}

    public BaseModel(int id, Date createTime, Date lastModify) {
        this.id = id;
        this.createTime = createTime;
        this.lastModify = lastModify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }
}
