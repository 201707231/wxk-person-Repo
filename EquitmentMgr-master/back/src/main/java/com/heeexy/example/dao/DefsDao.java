package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface DefsDao {

    List<JSONObject> listDefs();

    List<JSONObject> queryDefs(JSONObject json);

    int count(JSONObject json);

    int addDef(JSONObject json);

    int maxOf(JSONObject json);
}
