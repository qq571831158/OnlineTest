/**
 * Created by piaoxuehua on 2018/3/6.
 */
       // window.onload=function () {
       //     gotime();
       // }
window.onload = function () {
    // var msg = document.getElementById("msg");
    // if(msg!=""){
    //     alert(msg);
    // }
    // if(msg !== null){
    //     alert(msg.valu);
    // }
    var selected = [];
    var select = $("input[name = 'selected']");
    $(select).each(function (index,domEle) {
       selected.push($(domEle).val());
    });
    if (selected.length > 0){
        var selection = $("input[class ='selection']");
        $(selection).each(function (index,domEle) {
            for (var i = 0; i < selected.length ; i++){
                if (index == selected[i]){
                    $(domEle).attr('checked','checked');
                }
            }
        });
    }
};
function setTxtAnswer() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/test/setTxtAnswer",
        data: {
            testId: $('#testId').val(),
            qId:$('#nowQuestionNum').val(),
            answer: $("#txt").val()
        },
        dataType: "json",
        success: function(data) {
            if (data.code===200){
                console.log(data.msg);
            }
        },
        error: function(data) {
            alert("incorrect" + data)
        }
    });
}
function getNextQuestion(length) {
    // var selection = document.getElementsByClassName("selection:checked").value;
    // alert(selection);
    var testId = document.getElementById("testId").value;
    var nowQuestionId = document.getElementById("nowQuestionNum").value;
    if (nowQuestionId === length){
        alert("当前已是最后一题");
    } else {
        window.location.href= "http://localhost:8080/test/getNextQuestion?nowQuestionId="+nowQuestionId + "&testId=" + testId;
    }
}

function setAnswer(answer) {
    console.log(answer);
    var selection = $("input[class ='selection']");
    var answers = [];
    $(selection).each(function (index,domEle) {
        if ($(domEle).prop('checked')) {
            var answer = index + 65;
            answers.push(answer);
        }
    });
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/test/setAnswer",
        data: {
            testId: $('#testId').val(),
            qId:$('#nowQuestionNum').val(),
            answers: answers
        },
        dataType: "json",
        success: function(data) {
            if (data.code===200){
                console.log(data.msg);
            }
        },
        error: function(data) {
            alert("incorrect" + data)
        }
    });
}
function submitAnswer() {
    setTxtAnswer();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/test/submitAnswer",
        data: {
            testId: $('#testId').val()
        },
        dataType: "json",
        success: function(data) {
            if (data.code===200){
                console.log(data.msg);
                window.location.href = "http://localhost:8080/toStudentIndex";
            }else {
                alert(data.msg);
            }
        },
        error: function(data) {
            alert("incorrect" + data)
        }
    });
}
