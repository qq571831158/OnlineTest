package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Course;
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
public interface CourseDAO {

    String TABLE_NAME = "COURSE";

    String INSERT_FILEDS = "COURSE_NAME,TEA_ID,CREATE_TIME,COURSE_STU_NUM";

    String SELECT_FILEDS = "ID,"+INSERT_FILEDS;

    @Select({"select ",SELECT_FILEDS,"from",TABLE_NAME,"where id = #{id}"} )
    Course selectById(int id);

    @Select({"select",SELECT_FILEDS,"from",TABLE_NAME,"where tea_id = #{teacherId}"})
    List<Course> selectByTeaId(int teacherId);

    @Insert({"insert into",TABLE_NAME,"(",INSERT_FILEDS,") values(#{courseName},#{teaId},#{createTime},#{courseStuNum})"})
    int insertCourse(Course course);



    @Delete({"delete from",TABLE_NAME,"where id = #{id}"})
    int deleteCourse(int id);

}
