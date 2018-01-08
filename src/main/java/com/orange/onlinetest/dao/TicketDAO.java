package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Student;
import com.orange.onlinetest.model.Ticket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/8 9:33
 */
@Mapper
@Repository
public interface TicketDAO {
     String TABLE_NAME = "TICKET";

     String INSERT_FIELDS= "USER_ID,TICKET,EXPIRED,STATUS,USER_TYPE";

     String SELECT_FIELDS = "ID,"+INSERT_FIELDS;

    @Insert({"insert into ",TABLE_NAME , "(",INSERT_FIELDS,")values (#{userId},#{ticket},#{expired},#{status},#{userType})"})
    int addTicket(Ticket ticket);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where id=#{id}"})
    Ticket selectById(int id);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where ticket=#{ticket}"})
    Ticket selectByTicket(String ticket);

    @Update({"update",TABLE_NAME,"set status=#{status} where ticket=#{ticket}"})
    void updateStatus(@Param("ticket")String ticket,
                      @Param("status")int status);
}
