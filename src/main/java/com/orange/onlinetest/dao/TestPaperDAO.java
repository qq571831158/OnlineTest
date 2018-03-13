package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.TestPaper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestPaperDAO {

    String TABLE_NAME = "TESTPAPER";

    String INSERT_FILEDS = "TITLE,DESCRIBUTION,CONTENT,LAST_TIME,EXPIRE_TIME,TEACHER_ID,RESULT,CREATE_TIME,LAST_MODIFY,QUESTIONS,COURSE_ID,START_TIME,STATUS";

    String SELECT_FILEDS = "ID," + INSERT_FILEDS;

    @Select({"select",SELECT_FILEDS,"from",TABLE_NAME,"where id=#{id}"})
    TestPaper selectById(int id);

    @Select({"select",SELECT_FILEDS,"from",TABLE_NAME," where teacher_id = #{teacherId}"})
    List<TestPaper> selectByTeacherId(int teacherId);

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FILEDS,") values (#{title},#{describution},#{content},#{lastTime},#{expireTime},#{teacherId},#{result},#{createTime},#{lastModify},#{questions},#{courseId},#{startTime},#{status})"})
    void addTestpaper(TestPaper testpaper);

    @Update({"update testpaper set questions = #{questionId} where id = #{id}"})
    int updateTestQuestions(String questionId,int id);

    @Select({"select",SELECT_FILEDS,"from",TABLE_NAME,"where course_id = #{courseId}"})
    List<TestPaper>selectAllTestPaperByCourseId(int courseId);

}
