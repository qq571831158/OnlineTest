package com.orange.onlinetest.kafka.correctpaper;

import com.google.gson.Gson;
import com.orange.onlinetest.kafka.Message;
import com.orange.onlinetest.model.Answer;
import com.orange.onlinetest.model.Question;
import com.orange.onlinetest.model.Score;
import com.orange.onlinetest.responsedate.CorrectResult;
import com.orange.onlinetest.service.AnswerService;
import com.orange.onlinetest.service.ScoreService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.List;

public class CorrectPaperListener {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private ScoreService scoreService;

    @KafkaListener(topics = {"correctPaper"})
    public void listen(ConsumerRecord<?, ?> record) {
        Integer score = 0;
        String message = record.value().toString();
        Message message1 = new Gson().fromJson(message,Message.class);
        Answer answer = new Gson().fromJson(message1.getMsg(),Answer.class);
        List<Question> list =  answerService.getRigthAnswer(answer.getTestId());
        List<String> stuAnswerList = new ArrayList<>();
        List<CorrectResult> resultList = new ArrayList<>();
        String[] stuAnswerMap = answer.getAnswers().split(";");
        for (String s : stuAnswerMap){
            stuAnswerList.add(s.split(":")[1]);
        }
        for (int i = 0;i<list.size();i++){
            String correctAnswer = list.get(i).getAnswer();
            String stuAnswer = stuAnswerList.get(i);
            CorrectResult correctResult = new CorrectResult(list.get(i));
            correctResult.setCorrectResultId(i);
           if (list.get(i).getType() == Question.TYPE_SINGLE_SELECT || list.get(i).getType() == Question.TYPE_TRUE_FALSE){
               if (!stuAnswer.equals("") && !stuAnswer.equals("null") && correctAnswer.equals(stuAnswer)){
                   score += list.get(i).getScore();
                   correctResult.setIsRight((byte)1);
               }else {
                   correctResult.setIsRight((byte)0);
               }
               resultList.add(correctResult);
           }else if (list.get(i).getType() == Question.TYPE_MUILT_SELECT){
               if (!stuAnswer.equals("") && !stuAnswer.equals("null")){
                   if (correctAnswer.equals(stuAnswer)){
                       score += list.get(i).getScore();
                       correctResult.setIsRight((byte)1);
                   }else {
                       correctResult.setIsRight((byte)0);
                   }
               }
               resultList.add(correctResult);
           }else {

           }
        }
        //将选择题分插入数据库。
        Score score1 =  scoreService.addScore(answer.getTestId(),answer.getStudentId(),score);
        //如果插入成功，通知学生成绩出来了.
        if (score1 != null){

        }
    }
}
//1:A;2:C;3:A;4:C;5:B;6:D;7:C;8:C;9:null;10:null;11:null;12:null;13:null
//list :{A,C,B,B,A,C,D,A,C,B,D,A,C,D,B,C、D}
//答案：
// A，                              A                 √
// C,                               C                 √
// D,                               A
// C,                               C                 √
// C,                               B
// C,                               D
// C,                               C                 √
// B,                               C
// D,                              C
// C、D，                           C\D               √
// A、B，                           B\C
// A、B、C，                        C\D\E
// A、B、C                         A\B\C              √
