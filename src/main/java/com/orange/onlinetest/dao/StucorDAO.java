package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Stucor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by apple on 2018/3/11.
 */
@Mapper
@Repository
public interface StucorDAO {

    String TABLE_NAME = "STUCOR";

    String INSERT_FILEDS = "COURSE_ID,STUDENT_ID,CREATE_TIME,GRADE,TEST_ID,TEA_ID";

    String SELECT_FILEDS = "ID,"+INSERT_FILEDS;

    @Select({"select ",SELECT_FILEDS,"from",TABLE_NAME,"where id = #{id}"} )
    Stucor selectById(int id);

    @Select({"select ",SELECT_FILEDS,"from",TABLE_NAME,"where student_id = #{stuId}"} )
    List<Stucor> selectByStuId(int stuId);


    @Insert({"insert into",TABLE_NAME,"(",INSERT_FILEDS,") values(#{courseId},#{studentId},#{createTime},#{grade},#{testId},#{teaId})"})
    int insertStucor(Stucor course);



    @Delete({"delete from",TABLE_NAME,"where id = #{id}"})
    int deleteStucor(int id);
}
