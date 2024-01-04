package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface EventDao {
    public List<JSONObject> listEvent(JSONObject json);

    public int addEvent(JSONObject json);

    public  int addEventRela(JSONObject json);

}
