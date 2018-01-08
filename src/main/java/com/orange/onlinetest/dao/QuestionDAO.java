package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionDAO {

    String TABLE_NAME = "QUESTION";

    String INSERT_FILEDS = "TITLE,CONTENT,ANSWER,SCORE,TEACHER_ID,CREATE_TIME,LAST_MODIFY";

    String SELECT_FILEDS = "ID,"+INSERT_FILEDS;

    @Select({"select ",SELECT_FILEDS,"from",TABLE_NAME,"where id = #{id}"} )
    Question selectById(int id);

    @Select({"select",SELECT_FILEDS,"from",TABLE_NAME,"where teacher_id = #{teacherId}"})
    List<Question> selectByTeacherId(int teacherId);

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FILEDS,") values(#{title},#{content},#{answer},#{score},#{teacherId},#{createTime},#{lastModify})"})
    int insertQuesion(Question question);



    @Delete({"delete from",TABLE_NAME,"where id = #{id}"})
    int deleteQuestion(int id);

}
