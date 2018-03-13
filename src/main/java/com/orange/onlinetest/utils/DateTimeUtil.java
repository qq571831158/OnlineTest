package com.orange.onlinetest.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by apple on 2018/3/13.
 */
public class DateTimeUtil {
    public static String mysqlDate2ViewDate(Date date){
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date now = new Date();
        String d = sdf.format(date);
        String nowTime = sdf.format(now);
        if (d.substring(0,10).equals(nowTime.substring(0,10))){
            str = "今天"+d.substring(10);
        }else {
            str = sdf.format(date);
        }
        return str;
    }

}
