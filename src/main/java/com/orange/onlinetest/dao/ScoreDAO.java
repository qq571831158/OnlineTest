package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ScoreDAO {

    String TABLE_NAME = "SCORE";

    String INSERT_FIELDS = "STUDENT_ID,TEST_ID,SCORE,CREATE_TIME,LAST_MODIFY";

    String SELECT_FIELDS = "ID," + INSERT_FIELDS;

    @Select({"select ",SELECT_FIELDS,"from",TABLE_NAME,"where id = #{id}"})
    Score selectById(int id);

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values(#{studentId},#{testId},#{score},#{createTime},#{lastModify})"})
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertScore(Score score);




}
