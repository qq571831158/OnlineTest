package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Answer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnswerDAO {
    String TABLE_NAME = "ANSWER";

    String INSERT_FILEDS = "STUDENT_ID,TEST_ID,ANSWERS,CREATE_TIME,LAST_MODIFY";

    String SELECT_FILEDS = "ID,"+INSERT_FILEDS;

    @Select({"select ",SELECT_FILEDS,"from",TABLE_NAME,"where id = #{id}"} )
    Answer selectById(int id);

    @Select({"select",SELECT_FILEDS,"from",TABLE_NAME,"where student_id = #{stuId} and test_id = #{testId}"})
    Answer selectByStuIdAndTestId(int stuId,int testId);

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FILEDS,") values(#{studentId},#{testId},#{answers},#{createTime},#{lastModify})"})
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertAnswer(Answer answer);

}
