package com.orange.onlinetest.service;

import com.orange.onlinetest.dao.ScoreDAO;
import com.orange.onlinetest.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScoreService {
    @Autowired
    private ScoreDAO scoreDAO;

    public Score addScore(Integer stuId,Integer testId,Integer score){
        Score score1 = new Score();
        score1.setTestId(testId);
        score1.setStudentId(stuId);
        score1.setScore(score);
        Date date = new Date();
        score1.setCreateTime(date);
        score1.setLastModify(date);
        if (scoreDAO.insertScore(score1) > 0){
            return score1;
        }
        return null;
    }
}
