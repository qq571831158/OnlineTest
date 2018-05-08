/**
 * Created by piaoxuehua on 2018/3/6.
 */
       // window.onload=function () {
       //     gotime();
       // }

function getNextQuestion() {
    var testId = document.getElementById("testId").value;
    var nowQuestionId = document.getElementById("nowQuestionNum").value;
    window.location.href= "http://localhost:8080/test/getNextQuestion?nowQuestionId="+nowQuestionId + "&testId=" + testId;
}