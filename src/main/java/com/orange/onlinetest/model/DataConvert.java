package com.orange.onlinetest.model;

/**
 * Created by apple on 2018/4/17.
 */
public class DataConvert {
    public static String questionTypeInt2String(int type){

        if (type == Question.TYPE_SINGLE_SELECT){
            return  "单选题";
        }
        else if (type == Question.TYPE_MUILT_SELECT){
            return "多选题";
        }
        else if (type == Question.TYPE_TRUE_FALSE ){
            return "判断题";
        }
        else if (type == Question.TYPE_WENDA ){
            return  "问答题";
        }
        else if (type == Question.TYPE_ZHONGHE){
            return  "综合题";
        }
        else {
            return "其他题";
        }
    }
}
