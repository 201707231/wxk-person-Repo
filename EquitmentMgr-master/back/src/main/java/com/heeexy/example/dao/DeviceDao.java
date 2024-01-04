package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceDao {
    int  countDev(JSONObject jsonObject);

    List<JSONObject>     listDevice(JSONObject jsonObject);

    List<JSONObject>     queryDevice(JSONObject jsonObject);

    int updateDevice(JSONObject jsonObject);

    int addDevice(JSONObject jsonObject);

    List<JSONObject> listDevParts(JSONObject jsonObject);
}
