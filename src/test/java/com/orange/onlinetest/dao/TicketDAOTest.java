package com.orange.onlinetest.dao;

import com.orange.onlinetest.model.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/9 11:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketDAOTest {
    @Autowired
    private TicketDAO ticketDAO;
    @Test
    public void addTicket() {
    }

    @Test
    public void selectById() {
    }

    @Test
    public void selectByTicket() {
        Ticket ticket = ticketDAO.selectByTicket("17d1c2e2b5554d859b9900b69f6d1da1");
        System.out.println(ticket.getId());
        System.out.println(ticket.getUserId());
        System.out.println(ticket.getTicket());
        System.out.println(ticket.getStatus());
        System.out.println(ticket.getUserType());
    }

    @Test
    public void updateStatus() {
    }
}