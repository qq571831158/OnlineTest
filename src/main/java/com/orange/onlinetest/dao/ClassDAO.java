package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Class;
import com.orange.onlinetest.model.Question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClassDAO {

    String TABLE_NAME = "CLASS";

    String INSERT_FILEDS = "STUDENTS_NUM,NAME,STATUS,TEACHER_ID,CREATE_TIME,LAST_MODIFY";

    String SELECT_FILEDS = "ID,"+INSERT_FILEDS;

    @Select({"select ",SELECT_FILEDS,"from",TABLE_NAME,"where id = #{id}"} )
    Class selectById(int id);


    @Insert({"insert into",TABLE_NAME,"(",INSERT_FILEDS,") values(#{title},#{content},#{answer},#{score},#{teacherId},#{createTime},#{lastModify})"})
    int insertQuesion(Question question);



    @Delete({"delete from",TABLE_NAME,"where id = #{id}"})
    int deleteQuestion(int id);
}
