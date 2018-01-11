package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ScoreDAO {
    String TABLE_NAME = "SCORE";

    String INSERT_FIELDS = "STUDENT_ID,TESTPAPER_ID,SCORE,CREATE_TIME,LAST_MODIFY";

    String SELECT_FIELDS = "ID," + INSERT_FIELDS;

    @Select({"select ",SELECT_FIELDS,"from",TABLE_NAME,"where id = #{id}"})
    public Score selectById(int id);


}
