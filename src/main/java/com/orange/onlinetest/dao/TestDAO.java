package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestDAO {
    String TABLE_NAME = "TEST";

    String INSERT_FIELDS = "TEST_PAPER_ID,TEST_PAPER_NAME,TEST_PAPER_SCORE,COURSE_ID,QUESTIONS,START_TIME";

    String SELECT_FIELDS = "ID,"+INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{testPaperId},#{testPaperName},#{testPaperScore},#{courseId},#{questions})"})
    void addTest(Test test);

    @Select({"select ",SELECT_FIELDS,"from",TABLE_NAME,"where id = #{testId}"})
    Test selectById(int testId);
}
