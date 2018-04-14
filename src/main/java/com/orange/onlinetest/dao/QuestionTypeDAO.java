package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.QuestionType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/19 8:51
 */
@Mapper
@Repository
public interface QuestionTypeDAO {

    String TABLE_NAME = "QUESTIONTYPE";

    String INSERT_FIELDS = "NAME";

    String SELECT_FIELDS = "ID,"+ INSERT_FIELDS;

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME})
    List<QuestionType> selectAll();

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where id = #{id}"})
    QuestionType selectById(int id);

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values (#{id},#{name} )"})
    void addQuestionType(QuestionType questionType);

}
