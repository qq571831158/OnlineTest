package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherDAO {
    String TABLE_NAME = "TEACHER";

    String INSERT_FILEDS = "USERNAME,PASSWORD,SALT,NAME,PHONE_NUM,EMAIL,ACTIVE_CODE,STATUS,CREATE_TIME,LAST_MODIFY";

    String SELECT_FILEDS = "ID,"+INSERT_FILEDS;


    @Select({"select",SELECT_FILEDS,"from",TABLE_NAME,"where id = #{id}"})
    Teacher selectById(int id);

    @Select({"select ",SELECT_FILEDS,"from",TABLE_NAME,"where username = #{username}"})
    Teacher selectByUsername(String username);

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FILEDS,") values (#{username},#{password},#{salt},#{name},#{phoneNum},#{email},#{activeCode},#{status},#{createTime},#{lastModify})"})
    int addTeacher(Teacher teacher);

    @Update({"update",TABLE_NAME,"set password = #{password} where id = #{id}"})
    int updatePassword(String password,int id);

    @Delete({"delete from ",TABLE_NAME,"where id = #{id}"})
    int deleteTeacher(int id);
}
