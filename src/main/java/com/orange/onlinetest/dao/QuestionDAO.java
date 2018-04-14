package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.WeakHashMap;

@Mapper
@Repository
public interface QuestionDAO {

    String TABLE_NAME = "QUESTION";

    String INSERT_FILEDS = "TITLE,CONTENT,ANSWER,SCORE,QUESTION_TYPE,TEACHER_ID,CREATE_TIME,LAST_MODIFY";

    String SELECT_FILEDS = "ID,"+INSERT_FILEDS;

    @Select({"select ",SELECT_FILEDS,"from",TABLE_NAME,"where id = #{id}"} )
    Question selectById(int id);

    @Select({"select",SELECT_FILEDS,"from",TABLE_NAME,"where teacher_id = #{teacherId}"})
    List<Question> selectByTeacherId(int teacherId);

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FILEDS,") values(#{title},#{content},#{answer},#{score},#{questionType},#{teacherId},#{createTime},#{lastModify})"})
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="ID")
    int insertQuesion(Question question);



    @Delete({"delete from",TABLE_NAME,"where id = #{id}"})
    int deleteQuestion(int id);

}
