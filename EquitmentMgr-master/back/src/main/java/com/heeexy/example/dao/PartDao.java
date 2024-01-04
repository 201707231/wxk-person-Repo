package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PartDao {
    int  countPart(JSONObject jsonObject);

    List<JSONObject>     listPart(JSONObject jsonObject);

    List<JSONObject>     queryPart(JSONObject jsonObject);

    int updatePart(JSONObject jsonObject);

    int addPart(JSONObject jsonObject);

}
