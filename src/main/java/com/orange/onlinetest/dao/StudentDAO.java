package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentDAO {
    String TABLE_NAME = "student";

    String INSERT_FIELDS= "USERNAME,PASSWORD,SALT,NAME,AGE,SEX,STATUS,CLASS_ID,CREATE_TIME,LAST_MODIFY";

    String SELECT_FIELDS = "ID,"+INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(",INSERT_FIELDS,")values (#{username},#{password},#{salt},#{name},#{age},#{sex},#{status},#{classId},#{createTime},#{lastModify})"})
    int addUser(Student student);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where id=#{id}"})
    Student selectById(int id);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where username=#{username}"})
    Student selectByUsername(String name);

}
