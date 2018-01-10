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

    String INSERT_FIELDS = "QUESTION_ID,TESTPAPER_ID";

    String SELECT_FIELDS = "ID,"+INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{questionId},#{testPaperId})"})
    void addTest(Test test);

    @Select({"select ",SELECT_FIELDS,"from",TABLE_NAME,"where question_id = #{questionId}"})
    List<Test> selectByTestPaperId(int testPaperId);
}
