package com.orange.onlinetest.utils;

import java.util.LinkedList;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/17 12:07
 */
public class RedisKey {

    private static String SPLIT = "-";

    private static String BIZ_TEST = "TEST";

    public static String getTestKey(int userId,int testPaperId){
        return BIZ_TEST + SPLIT + String.valueOf(userId)+SPLIT+String.valueOf(testPaperId);
    }

}
