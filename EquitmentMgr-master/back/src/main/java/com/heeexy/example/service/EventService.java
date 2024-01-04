package com.heeexy.example.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.heeexy.example.dao.EventDao;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.pattern.DateConverter;
import ch.qos.logback.core.util.TimeUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Date;

/**
 * @author: heeexy
 * @description: 用户/角色/权限
 * @date: 2017/11/2 10:18
 */
@Service
public class EventService {
    @Autowired
    private EventDao eventDao;

    /**
     * 事件列表
     */
    public JSONObject listEvent(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        List<JSONObject> list = eventDao.listEvent(jsonObject);
        return CommonUtil.successPage(jsonObject, list, 1);
    }

    @Transactional(rollbackForClassName = "Exception")
    public JSONObject addEvent(JSONObject jsonObject) throws Exception {
        try {
            String eventID = generateEventID(jsonObject);
            jsonObject.put("eventID", eventID);
            int i = eventDao.addEvent(jsonObject);
            if (i != 1) {
                throw new Exception("addEvent Error");
            }
            JSONArray relatives = jsonObject.getJSONArray("relative");
            if (relatives.isEmpty() || relatives.size() <= 0) {
                return CommonUtil.successJson();
            }
            for (int j = 0; j < relatives.size(); j++) {
                JSONObject json = relatives.getJSONObject(j);
                json.put("eventID", eventID);
                if (!json.containsKey("devID")) {
                    json.put("devID", null);
                }
                if (!json.containsKey("partID")) {
                    json.put("partID", null);
                }

                i = eventDao.addEventRela(json);
                if (i != 1) {
                    throw new Exception("addEventRela Error");
                }
            }
        } catch (Exception e) {
            throw new Exception("addEvent/addEventRela Error");
        }
        return CommonUtil.successJson();
    }

    public String generateEventID(JSONObject json) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date evtTime = null;
        String timeStr="";
        if (!json.containsKey("eventTime")) {
            evtTime = new Date();
            json.put("eventTime", df2.format(evtTime));
        } else {
            try {
                evtTime = df.parse(json.getString("eventTime"));
            } catch (Exception ex) {
                evtTime=new Date();
            }
        }
        timeStr=df2.format(evtTime);
        return String.format("%s-%s", json.getString("eventTypeID"), timeStr);
    }

}
