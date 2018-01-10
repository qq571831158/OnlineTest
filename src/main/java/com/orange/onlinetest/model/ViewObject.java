package com.orange.onlinetest.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author orange
 * @Description
 * @Date: 2018/1/10 10:33
 */
public class ViewObject {
    Map<String,Object> map = new HashMap<>();

    public void set(String key,Object o){
        map.put(key,o);
    }

    public Object get(String key){
        return map.get(key);
    }
}
